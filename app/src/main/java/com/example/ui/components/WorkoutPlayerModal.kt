package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.AccentGold
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.viewmodel.ActiveWorkoutState

@Composable
fun WorkoutPlayerDialog(
  workoutState: ActiveWorkoutState,
  onTogglePause: () -> Unit,
  onNext: () -> Unit,
  onPrev: () -> Unit,
  onCompleteEarly: () -> Unit,
  onClose: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler {
    onClose()
  }

  Dialog(
    onDismissRequest = onClose,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Surface(
      modifier = modifier
        .fillMaxSize()
        .testTag("workout_player_dialog"),
      color = MaterialTheme.colorScheme.background
    ) {
      if (workoutState.isCompleted) {
        WorkoutCompletedView(
          workoutState = workoutState,
          onClose = onClose
        )
      } else {
        ActiveWorkoutInProgressView(
          workoutState = workoutState,
          onTogglePause = onTogglePause,
          onNext = onNext,
          onPrev = onPrev,
          onCompleteEarly = onCompleteEarly,
          onClose = onClose
        )
      }
    }
  }
}

@Composable
private fun ActiveWorkoutInProgressView(
  workoutState: ActiveWorkoutState,
  onTogglePause: () -> Unit,
  onNext: () -> Unit,
  onPrev: () -> Unit,
  onCompleteEarly: () -> Unit,
  onClose: () -> Unit
) {
  val routine = workoutState.routine
  val currentStep = routine.steps.getOrNull(workoutState.currentStepIndex) ?: return
  val totalSteps = routine.steps.size
  val progressFraction = (workoutState.currentStepIndex + 1).toFloat() / totalSteps.toFloat()

  val totalStepSeconds = currentStep.durationSeconds.toFloat().coerceAtLeast(1f)
  val remainingFraction = (workoutState.secondsRemaining / totalStepSeconds).coerceIn(0f, 1f)
  val animatedAngle by animateFloatAsState(
    targetValue = remainingFraction * 360f,
    label = "TimerAngle"
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Header Row
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(
        onClick = onClose,
        modifier = Modifier.testTag("close_workout_button")
      ) {
        Icon(
          imageVector = Icons.Filled.Close,
          contentDescription = "Exit Workout",
          tint = MaterialTheme.colorScheme.onSurface
        )
      }

      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = routine.title,
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "Exercise ${workoutState.currentStepIndex + 1} of $totalSteps",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.primary
        )
      }

      OutlinedButton(
        onClick = onCompleteEarly,
        modifier = Modifier.testTag("finish_early_button"),
        contentPadding = ButtonDefaults.TextButtonContentPadding
      ) {
        Text("Done", style = MaterialTheme.typography.labelSmall)
      }
    }

    // Step Progress Bar
    LinearProgressIndicator(
      progress = { progressFraction },
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp)
        .height(6.dp)
        .clip(RoundedCornerShape(3.dp)),
      color = EmeraldGreenPrimary,
      trackColor = MaterialTheme.colorScheme.surfaceVariant
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Circular Countdown Timer
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .size(200.dp)
        .padding(8.dp)
    ) {
      val primaryColor = MaterialTheme.colorScheme.primary
      val trackColor = MaterialTheme.colorScheme.surfaceVariant

      Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 12.dp.toPx()
        val diameter = size.minDimension - strokeWidth
        val topLeft = Offset(
          (size.width - diameter) / 2,
          (size.height - diameter) / 2
        )
        // Background track circle
        drawArc(
          color = trackColor,
          startAngle = -90f,
          sweepAngle = 360f,
          useCenter = false,
          topLeft = topLeft,
          size = Size(diameter, diameter),
          style = Stroke(width = strokeWidth)
        )
        // Animated green countdown arc
        drawArc(
          color = primaryColor,
          startAngle = -90f,
          sweepAngle = animatedAngle,
          useCenter = false,
          topLeft = topLeft,
          size = Size(diameter, diameter),
          style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
      }

      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val minutes = workoutState.secondsRemaining / 60
        val seconds = workoutState.secondsRemaining % 60
        Text(
          text = String.format("%02d:%02d", minutes, seconds),
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 44.sp,
            letterSpacing = (-1).sp
          ),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = if (workoutState.isPaused) "PAUSED" else "ACTIVE",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
          color = if (workoutState.isPaused) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Exercise Name Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
      shape = RoundedCornerShape(16.dp)
    ) {
      Column(
        modifier = Modifier.padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Surface(
          color = MaterialTheme.colorScheme.primaryContainer,
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(
            text = currentStep.targetArea,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = currentStep.name,
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface,
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = currentStep.instructions,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Ergonomic / Desk Tip Banner
    Surface(
      color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Filled.Info,
          contentDescription = "Ergonomic Tip",
          tint = MaterialTheme.colorScheme.secondary,
          modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
          text = "Office Tip: ${currentStep.ergonomicTip}",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSecondaryContainer
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Player Controls (Prev, Pause/Play, Next)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically
    ) {
      FilledTonalButton(
        onClick = onPrev,
        enabled = workoutState.currentStepIndex > 0,
        shape = CircleShape,
        modifier = Modifier
          .size(54.dp)
          .testTag("workout_prev_button")
      ) {
        Icon(
          imageVector = Icons.Filled.SkipPrevious,
          contentDescription = "Previous Exercise"
        )
      }

      Button(
        onClick = onTogglePause,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
        modifier = Modifier
          .size(72.dp)
          .testTag("workout_play_pause_button")
      ) {
        Icon(
          imageVector = if (workoutState.isPaused) Icons.Filled.PlayArrow else Icons.Filled.Pause,
          contentDescription = if (workoutState.isPaused) "Resume" else "Pause",
          tint = Color.White,
          modifier = Modifier.size(36.dp)
        )
      }

      FilledTonalButton(
        onClick = onNext,
        shape = CircleShape,
        modifier = Modifier
          .size(54.dp)
          .testTag("workout_next_button")
      ) {
        Icon(
          imageVector = Icons.Filled.SkipNext,
          contentDescription = "Next Exercise"
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

@Composable
private fun WorkoutCompletedView(
  workoutState: ActiveWorkoutState,
  onClose: () -> Unit
) {
  val routine = workoutState.routine
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Box(
      modifier = Modifier
        .size(90.dp)
        .clip(CircleShape)
        .background(EmeraldGreenPrimary),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = Icons.Filled.CheckCircle,
        contentDescription = "Completed",
        tint = Color.White,
        modifier = Modifier.size(56.dp)
      )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
      text = "Shabash! 10-Minute Desk Workout Done! 🎉",
      style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
      color = MaterialTheme.colorScheme.onSurface,
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = "You just recharged your mind and spinal discs during your busy office hours.",
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Stats Grid
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Card(
        modifier = Modifier.weight(1f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp)
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Icon(
            imageVector = Icons.Filled.LocalFireDepartment,
            contentDescription = "Calories",
            tint = AccentGold,
            modifier = Modifier.size(28.dp)
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "+${routine.caloriesBurned} kcal",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Energy Burned",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      Card(
        modifier = Modifier.weight(1f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp)
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Icon(
            imageVector = Icons.Filled.Timer,
            contentDescription = "Time",
            tint = EmeraldGreenPrimary,
            modifier = Modifier.size(28.dp)
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "10 Mins",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Active Movement",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Surface(
      color = MaterialTheme.colorScheme.primaryContainer,
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Filled.SelfImprovement,
          contentDescription = "Daily Habit Ticked",
          tint = MaterialTheme.colorScheme.onPrimaryContainer,
          modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
          text = "Daily Habit '10-Min Desk Workout' marked as complete! Your daily streak is safe.",
          style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
          color = MaterialTheme.colorScheme.onPrimaryContainer
        )
      }
    }

    Spacer(modifier = Modifier.height(32.dp))

    Button(
      onClick = onClose,
      colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
      shape = RoundedCornerShape(14.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)
        .testTag("workout_back_to_desk_button")
    ) {
      Text(
        text = "Return to Dashboard",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = Color.White
      )
    }
  }
}
