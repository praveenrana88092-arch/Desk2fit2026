package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.Desk2FitBottomNavBar
import com.example.ui.components.Desk2FitTopBar
import com.example.ui.components.WorkoutPlayerDialog
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.MealsScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.WorkoutsScreen
import com.example.ui.theme.Desk2FitTheme
import com.example.viewmodel.Desk2FitViewModel
import com.example.viewmodel.ScreenTab

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Desk2FitTheme {
        Desk2FitApp()
      }
    }
  }
}

@Composable
fun Desk2FitApp(viewModel: Desk2FitViewModel = viewModel()) {
  val state by viewModel.uiState.collectAsStateWithLifecycle()
  val snackbarHostState = remember { SnackbarHostState() }

  // Handle hardware back button to navigate back to Home if on another tab
  BackHandler(enabled = state.currentTab != ScreenTab.DASHBOARD && state.activeWorkout == null) {
    viewModel.selectTab(ScreenTab.DASHBOARD)
  }

  // Handle ephemeral notifications / messages
  LaunchedEffect(state.snackMessage) {
    state.snackMessage?.let { msg ->
      snackbarHostState.showSnackbar(
        message = msg,
        duration = SnackbarDuration.Short
      )
      viewModel.clearSnackMessage()
    }
  }

  Scaffold(
    topBar = {
      Desk2FitTopBar(streakCount = state.streakDaysCount)
    },
    bottomBar = {
      Desk2FitBottomNavBar(
        currentTab = state.currentTab,
        onTabSelected = viewModel::selectTab
      )
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    },
    modifier = Modifier.fillMaxSize()
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      Crossfade(
        targetState = state.currentTab,
        label = "TabCrossfade"
      ) { tab ->
        when (tab) {
          ScreenTab.DASHBOARD -> DashboardScreen(
            state = state,
            onStartWorkout = viewModel::startWorkout,
            onToggleHabit = viewModel::toggleHabit,
            onIncrementWater = viewModel::incrementWater,
            onDecrementWater = viewModel::decrementWater,
            onLogStandUp = viewModel::logStandUp,
            onNavigateTab = viewModel::selectTab
          )
          ScreenTab.WORKOUTS -> WorkoutsScreen(
            selectedCategory = state.selectedWorkoutCategory,
            onCategorySelected = viewModel::setWorkoutCategory,
            onStartWorkout = viewModel::startWorkout
          )
          ScreenTab.MEALS -> MealsScreen(
            selectedDiet = state.selectedDietTab,
            onDietSelected = viewModel::setDietTab,
            waterCount = state.waterGlassesLogged,
            waterGoal = state.userProfile.dailyWaterGoalGlasses,
            onIncrementWater = viewModel::incrementWater
          )
          ScreenTab.PROGRESS -> ProgressScreen(
            state = state
          )
          ScreenTab.PROFILE -> ProfileScreen(
            userProfile = state.userProfile,
            onUpdateProfile = viewModel::updateUserProfile
          )
        }
      }

      // 10-Minute Interactive Workout Player Modal
      state.activeWorkout?.let { active ->
        WorkoutPlayerDialog(
          workoutState = active,
          onTogglePause = viewModel::togglePauseWorkout,
          onNext = viewModel::nextStep,
          onPrev = viewModel::prevStep,
          onCompleteEarly = viewModel::completeWorkout,
          onClose = viewModel::closeActiveWorkout
        )
      }
    }
  }
}
