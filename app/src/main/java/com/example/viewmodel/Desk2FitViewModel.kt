package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SampleData
import com.example.model.BadgeItem
import com.example.model.DailyHabit
import com.example.model.DietType
import com.example.model.UserProfile
import com.example.model.WorkoutCategory
import com.example.model.WorkoutRoutine
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class ScreenTab(val title: String) {
  DASHBOARD("Home"),
  WORKOUTS("Workouts"),
  MEALS("Meals"),
  PROGRESS("Progress"),
  PROFILE("Profile")
}

data class ActiveWorkoutState(
  val routine: WorkoutRoutine,
  val currentStepIndex: Int = 0,
  val secondsRemaining: Int = 60,
  val isPaused: Boolean = false,
  val isCompleted: Boolean = false,
  val soundAlert: Boolean = true
)

data class UiState(
  val currentTab: ScreenTab = ScreenTab.DASHBOARD,
  val activeWorkout: ActiveWorkoutState? = null,
  val streakDaysCount: Int = 5,
  val weeklyCompletedDays: List<Boolean> = listOf(true, true, true, true, true, false, false), // Mon-Fri done
  val waterGlassesLogged: Int = 6,
  val standUpCount: Int = 4,
  val dailyHabits: List<DailyHabit> = SampleData.defaultHabits,
  val selectedDietTab: DietType = DietType.VEGETARIAN,
  val selectedWorkoutCategory: WorkoutCategory = WorkoutCategory.ALL,
  val userProfile: UserProfile = UserProfile(),
  val badges: List<BadgeItem> = SampleData.defaultBadges,
  val totalCaloriesBurned: Int = 680,
  val weeklyWorkoutMinutes: Int = 120,
  val ergonomicsScore: Int = 86,
  val snackMessage: String? = null
)

class Desk2FitViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(UiState())
  val uiState: StateFlow<UiState> = _uiState.asStateFlow()

  private var workoutTimerJob: Job? = null

  fun selectTab(tab: ScreenTab) {
    _uiState.update { it.copy(currentTab = tab) }
  }

  fun setDietTab(dietType: DietType) {
    _uiState.update { it.copy(selectedDietTab = dietType) }
  }

  fun setWorkoutCategory(category: WorkoutCategory) {
    _uiState.update { it.copy(selectedWorkoutCategory = category) }
  }

  fun toggleHabit(habitId: String) {
    _uiState.update { current ->
      val updated = current.dailyHabits.map {
        if (it.id == habitId) it.copy(isCompleted = !it.isCompleted) else it
      }
      val allCompleted = updated.all { it.isCompleted }
      val message = if (allCompleted) "Awesome! All daily office wellness habits completed!" else "Habit updated!"
      current.copy(dailyHabits = updated, snackMessage = message)
    }
  }

  fun incrementWater() {
    _uiState.update { current ->
      val newCount = (current.waterGlassesLogged + 1).coerceAtMost(16)
      val habits = current.dailyHabits.map {
        if (it.id == "h2" && newCount >= current.userProfile.dailyWaterGoalGlasses) {
          it.copy(isCompleted = true)
        } else it
      }
      current.copy(
        waterGlassesLogged = newCount,
        dailyHabits = habits,
        snackMessage = "Logged +1 glass of water (${newCount}/${current.userProfile.dailyWaterGoalGlasses}) 💧"
      )
    }
  }

  fun decrementWater() {
    _uiState.update { current ->
      val newCount = (current.waterGlassesLogged - 1).coerceAtLeast(0)
      current.copy(waterGlassesLogged = newCount)
    }
  }

  fun logStandUp() {
    _uiState.update { current ->
      val newCount = (current.standUpCount + 1).coerceAtMost(12)
      val habits = current.dailyHabits.map {
        if (it.id == "h3" && newCount >= current.userProfile.hourlyStandUpGoal) {
          it.copy(isCompleted = true)
        } else it
      }
      current.copy(
        standUpCount = newCount,
        dailyHabits = habits,
        snackMessage = "Hourly stand-up & walk logged! ($newCount/${current.userProfile.hourlyStandUpGoal} hrs) 🚶"
      )
    }
  }

  fun startWorkout(routine: WorkoutRoutine) {
    workoutTimerJob?.cancel()
    val initialSeconds = routine.steps.firstOrNull()?.durationSeconds ?: 60
    val active = ActiveWorkoutState(
      routine = routine,
      currentStepIndex = 0,
      secondsRemaining = initialSeconds,
      isPaused = false,
      isCompleted = false
    )
    _uiState.update { it.copy(activeWorkout = active) }
    runWorkoutTimer()
  }

  private fun runWorkoutTimer() {
    workoutTimerJob?.cancel()
    workoutTimerJob = viewModelScope.launch {
      while (true) {
        delay(1000)
        val active = _uiState.value.activeWorkout ?: break
        if (active.isPaused || active.isCompleted) continue

        if (active.secondsRemaining > 1) {
          _uiState.update { current ->
            current.copy(
              activeWorkout = current.activeWorkout?.copy(
                secondsRemaining = current.activeWorkout.secondsRemaining - 1
              )
            )
          }
        } else {
          // Current step finished
          val nextIndex = active.currentStepIndex + 1
          if (nextIndex < active.routine.steps.size) {
            val nextStepDuration = active.routine.steps[nextIndex].durationSeconds
            _uiState.update { current ->
              current.copy(
                activeWorkout = current.activeWorkout?.copy(
                  currentStepIndex = nextIndex,
                  secondsRemaining = nextStepDuration
                )
              )
            }
          } else {
            // Completed whole routine!
            completeWorkout()
            break
          }
        }
      }
    }
  }

  fun togglePauseWorkout() {
    _uiState.update { current ->
      val active = current.activeWorkout ?: return@update current
      current.copy(activeWorkout = active.copy(isPaused = !active.isPaused))
    }
  }

  fun nextStep() {
    val active = _uiState.value.activeWorkout ?: return
    val nextIndex = active.currentStepIndex + 1
    if (nextIndex < active.routine.steps.size) {
      val dur = active.routine.steps[nextIndex].durationSeconds
      _uiState.update { it.copy(activeWorkout = active.copy(currentStepIndex = nextIndex, secondsRemaining = dur)) }
    } else {
      completeWorkout()
    }
  }

  fun prevStep() {
    val active = _uiState.value.activeWorkout ?: return
    val prevIndex = (active.currentStepIndex - 1).coerceAtLeast(0)
    val dur = active.routine.steps[prevIndex].durationSeconds
    _uiState.update { it.copy(activeWorkout = active.copy(currentStepIndex = prevIndex, secondsRemaining = dur)) }
  }

  fun completeWorkout() {
    workoutTimerJob?.cancel()
    val active = _uiState.value.activeWorkout ?: return
    val calories = active.routine.caloriesBurned
    _uiState.update { current ->
      val updatedHabits = current.dailyHabits.map {
        if (it.id == "h1") it.copy(isCompleted = true) else it
      }
      val newBurned = current.totalCaloriesBurned + calories
      val newMinutes = current.weeklyWorkoutMinutes + active.routine.durationMinutes
      current.copy(
        activeWorkout = active.copy(isCompleted = true, secondsRemaining = 0),
        dailyHabits = updatedHabits,
        totalCaloriesBurned = newBurned,
        weeklyWorkoutMinutes = newMinutes,
        snackMessage = "Shabash! 10-Minute Desk Workout Completed! +$calories kcal burned 🔥"
      )
    }
  }

  fun closeActiveWorkout() {
    workoutTimerJob?.cancel()
    _uiState.update { it.copy(activeWorkout = null) }
  }

  fun updateUserProfile(profile: UserProfile) {
    _uiState.update {
      it.copy(
        userProfile = profile,
        selectedDietTab = profile.primaryDiet,
        snackMessage = "Profile settings saved!"
      )
    }
  }

  fun clearSnackMessage() {
    _uiState.update { it.copy(snackMessage = null) }
  }

  override fun onCleared() {
    super.onCleared()
    workoutTimerJob?.cancel()
  }
}
