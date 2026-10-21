package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SampleData
import com.example.model.DailyHabit
import com.example.model.WorkoutRoutine
import com.example.ui.theme.AccentGold
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.WaterBlue
import com.example.viewmodel.ScreenTab
import com.example.viewmodel.UiState

@Composable
fun DashboardScreen(
  state: UiState,
  onStartWorkout: (WorkoutRoutine) -> Unit,
  onToggleHabit: (String) -> Unit,
  onIncrementWater: () -> Unit,
  onDecrementWater: () -> Unit,
  onLogStandUp: () -> Unit,
  onNavigateTab: (ScreenTab) -> Unit,
  modifier: Modifier = Modifier
) {
  val defaultHeroWorkout = SampleData.routines.first()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("dashboard_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. User Greeting & Context Header
    item {
      GreetingHeader(
        name = state.userProfile.name,
        role = state.userProfile.role,
        city = state.userProfile.companyCity,
        deskHours = state.userProfile.deskHours
      )
    }

    // 2. Hero Card: 10-Minute Desk Workout
    item {
      HeroWorkoutCard(
        routine = defaultHeroWorkout,
        onStart = { onStartWorkout(defaultHeroWorkout) }
      )
    }

    // 3. Daily Streak & Weekly Days Tracker
    item {
      StreakTrackingCard(
        streakCount = state.streakDaysCount,
        weeklyDays = state.weeklyCompletedDays
      )
    }

    // 4. Quick Office Hydration & Movement Loggers
    item {
      QuickLoggersRow(
        waterCount = state.waterGlassesLogged,
        waterGoal = state.userProfile.dailyWaterGoalGlasses,
        standUpCount = state.standUpCount,
        standUpGoal = state.userProfile.hourlyStandUpGoal,
        onIncrementWater = onIncrementWater,
        onDecrementWater = onDecrementWater,
        onLogStandUp = onLogStandUp
      )
    }

    // 5. Daily Office Wellness Checklist
    item {
      DailyHabitsChecklistCard(
        habits = state.dailyHabits,
        onToggleHabit = onToggleHabit
      )
    }

    // 6. Indian Chai Time Swap of the Day
    item {
      ChaiSwapSpotlightCard(
        onViewAllMeals = { onNavigateTab(ScreenTab.MEALS) }
      )
    }

    // 7. Ergonomics Daily Tip
    item {
      ErgonomicTipCard()
    }
  }
}

@Composable
private fun GreetingHeader(
  name: String,
  role: String,
  city: String,
  deskHours: String
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column {
      Text(
        text = "Namaste, $name! 🙏",
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = "$role • $city",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Text(
        text = "Desk Shift: $deskHours",
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
        color = MaterialTheme.colorScheme.primary
      )
    }
  }
}

@Composable
private fun HeroWorkoutCard(
  routine: WorkoutRoutine,
  onStart: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("hero_workout_card"),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = EmeraldGreenPrimary),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    Column(
      modifier = Modifier.padding(20.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          color = Color.White.copy(alpha = 0.2f),
          shape = RoundedCornerShape(8.dp)
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Filled.Timer,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "10-MIN DAILY ROUTINE",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
              color = Color.White
            )
          }
        }

        Surface(
          color = AccentGold,
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(
            text = "🔥 45 kcal",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      Text(
        text = routine.title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontWeight = FontWeight.ExtraBold,
          fontSize = 22.sp
        ),
        color = Color.White
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = routine.subtitle,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White.copy(alpha = 0.9f)
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "Includes: Neck Chin Tucks, Scapular Retractions, Desk Chest Openers, Seated Twists & Eye Palming.",
        style = MaterialTheme.typography.bodySmall,
        color = Color.White.copy(alpha = 0.8f)
      )

      Spacer(modifier = Modifier.height(18.dp))

      Button(
        onClick = onStart,
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.White,
          contentColor = EmeraldGreenPrimary
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
          .testTag("start_hero_workout_button")
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Start Workout",
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Start 10-Min Posture Fix",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
          )
        }
      }
    }
  }
}

@Composable
private fun StreakTrackingCard(
  streakCount: Int,
  weeklyDays: List<Boolean>
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("streak_tracking_card"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier.padding(18.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
              .background(AccentGold.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Filled.LocalFireDepartment,
              contentDescription = null,
              tint = AccentGold,
              modifier = Modifier.size(26.dp)
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = "$streakCount Days Active Streak!",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "Target: 7/7 office days completed",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Mon to Sun row
      val dayLabels = listOf("M", "T", "W", "T", "F", "S", "S")
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        dayLabels.forEachIndexed { index, label ->
          val isDone = weeklyDays.getOrElse(index) { false }
          val isToday = index == 4 // Friday in current 5-day cycle

          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(
                  when {
                    isDone -> EmeraldGreenPrimary
                    isToday -> MaterialTheme.colorScheme.primaryContainer
                    else -> MaterialTheme.colorScheme.surfaceVariant
                  }
                ),
              contentAlignment = Alignment.Center
            ) {
              if (isDone) {
                Icon(
                  imageVector = Icons.Filled.Check,
                  contentDescription = "Day $label Done",
                  tint = Color.White,
                  modifier = Modifier.size(20.dp)
                )
              } else {
                Text(
                  text = label,
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = label,
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }
  }
}

@Composable
private fun QuickLoggersRow(
  waterCount: Int,
  waterGoal: Int,
  standUpCount: Int,
  standUpGoal: Int,
  onIncrementWater: () -> Unit,
  onDecrementWater: () -> Unit,
  onLogStandUp: () -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    // Water Logger Card
    Card(
      modifier = Modifier
        .weight(1f)
        .testTag("water_logger_card"),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Filled.Opacity,
            contentDescription = "Water",
            tint = WaterBlue,
            modifier = Modifier.size(22.dp)
          )
          Text(
            text = "$waterCount/$waterGoal glasses",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "AC Hydration",
          style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "2.5L office goal",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          FilledTonalIconButton(
            onClick = onDecrementWater,
            enabled = waterCount > 0,
            modifier = Modifier
              .size(36.dp)
              .testTag("water_decrement_button")
          ) {
            Icon(imageVector = Icons.Filled.Remove, contentDescription = "Decrease Water")
          }
          FilledTonalIconButton(
            onClick = onIncrementWater,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
              containerColor = MaterialTheme.colorScheme.primaryContainer,
              contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier
              .size(36.dp)
              .testTag("water_increment_button")
          ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Water Glass")
          }
        }
      }
    }

    // Stand-Up Logger Card
    Card(
      modifier = Modifier
        .weight(1f)
        .testTag("standup_logger_card"),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Filled.DirectionsWalk,
            contentDescription = "Stand Up",
            tint = EmeraldGreenPrimary,
            modifier = Modifier.size(22.dp)
          )
          Text(
            text = "$standUpCount/$standUpGoal hrs",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "Hourly Walks",
          style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "2 mins per hour",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
          onClick = onLogStandUp,
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .testTag("log_standup_button")
        ) {
          Text("+1 Hour Walk", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
        }
      }
    }
  }
}

@Composable
private fun DailyHabitsChecklistCard(
  habits: List<DailyHabit>,
  onToggleHabit: (String) -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("habits_checklist_card"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Today's Office Habits",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Small daily habits beat sitting all day",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
        val completedCount = habits.count { it.isCompleted }
        Surface(
          color = MaterialTheme.colorScheme.primaryContainer,
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(
            text = "$completedCount/${habits.size} Done",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      habits.forEach { habit ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onToggleHabit(habit.id) }
            .padding(vertical = 6.dp, horizontal = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Checkbox(
            checked = habit.isCompleted,
            onCheckedChange = { onToggleHabit(habit.id) },
            colors = CheckboxDefaults.colors(
              checkedColor = EmeraldGreenPrimary,
              checkmarkColor = Color.White
            ),
            modifier = Modifier.testTag("habit_checkbox_${habit.id}")
          )
          Spacer(modifier = Modifier.width(6.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = habit.title,
              style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (habit.isCompleted) FontWeight.Medium else FontWeight.SemiBold
              ),
              color = if (habit.isCompleted) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = habit.subtitle,
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }
  }
}

@Composable
private fun ChaiSwapSpotlightCard(
  onViewAllMeals: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("chai_swap_spotlight_card"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Filled.Restaurant,
            contentDescription = null,
            tint = EmeraldGreenPrimary,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "4 PM Chai Break Swap",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
        }
        Surface(
          color = AccentGold.copy(alpha = 0.2f),
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(
            text = "Save 190 kcal!",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Unhealthy
        Column(modifier = Modifier.weight(1f)) {
          Text("Skip Canteen Samosa", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.error)
          Text("280 kcal • Deep Fried", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text("➔", style = MaterialTheme.typography.titleMedium, color = EmeraldGreenPrimary, modifier = Modifier.padding(horizontal = 8.dp))
        // Healthy Swap
        Column(modifier = Modifier.weight(1.2f)) {
          Text("Choose Spiced Makhana", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = EmeraldGreenPrimary)
          Text("90 kcal • High Calcium", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      FilledTonalButton(
        onClick = onViewAllMeals,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("View Full Indian Meal Plans", style = MaterialTheme.typography.labelMedium)
          Spacer(modifier = Modifier.width(6.dp))
          Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
        }
      }
    }
  }
}

@Composable
private fun ErgonomicTipCard() {
  Surface(
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
    shape = RoundedCornerShape(12.dp),
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier.padding(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.Filled.Lightbulb,
        contentDescription = "Ergonomic Tip",
        tint = EmeraldGreenPrimary,
        modifier = Modifier.size(24.dp)
      )
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
          text = "Desk Ergonomics Tip of the Day",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "Your laptop screen top should align with eye level. Prop it up on 2 books or a stand, and use an external keyboard to avoid hunching.",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  }
}
