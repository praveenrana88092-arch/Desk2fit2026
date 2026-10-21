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
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DietType
import com.example.model.UserProfile
import com.example.ui.theme.EmeraldGreenPrimary

@Composable
fun ProfileScreen(
  userProfile: UserProfile,
  onUpdateProfile: (UserProfile) -> Unit,
  modifier: Modifier = Modifier
) {
  var showEditDialog by remember { mutableStateOf(false) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("profile_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. User Profile Header Card
    item {
      ProfileCard(
        profile = userProfile,
        onEditClick = { showEditDialog = true }
      )
    }

    // 2. Primary Diet Preference Selector
    item {
      DietPreferenceSelector(
        selectedDiet = userProfile.primaryDiet,
        onSelectDiet = { newDiet ->
          onUpdateProfile(userProfile.copy(primaryDiet = newDiet))
        }
      )
    }

    // 3. Daily Goals Settings Card
    item {
      DailyGoalsCard(profile = userProfile)
    }

    // 4. Desk Habit Reminders Card
    item {
      RemindersCard(
        remindersEnabled = userProfile.remindersEnabled,
        onToggleReminders = { enabled ->
          onUpdateProfile(userProfile.copy(remindersEnabled = enabled))
        }
      )
    }

    // 5. Office Ergonomics Guide & Checklist
    item {
      ErgonomicsGuideCard()
    }

    // 6. App Info & Prototype Disclaimer
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "Desk2Fit • Free Prototype Edition",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Specially crafted for Indian corporate professionals • Version 1.0",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }
  }

  if (showEditDialog) {
    EditProfileDialog(
      currentProfile = userProfile,
      onDismiss = { showEditDialog = false },
      onSave = { updated ->
        onUpdateProfile(updated)
        showEditDialog = false
      }
    )
  }
}

@Composable
private fun ProfileCard(
  profile: UserProfile,
  onEditClick: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(18.dp),
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
          Box(
            modifier = Modifier
              .size(60.dp)
              .clip(CircleShape)
              .background(EmeraldGreenPrimary),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = profile.name.take(2).uppercase(),
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            )
          }

          Spacer(modifier = Modifier.width(14.dp))

          Column {
            Text(
              text = profile.name,
              style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = profile.role,
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        IconButton(
          onClick = onEditClick,
          modifier = Modifier.testTag("edit_profile_button")
        ) {
          Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit Profile",
            tint = EmeraldGreenPrimary
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Filled.LocationOn,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = profile.companyCity,
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Filled.Schedule,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Working Hours: ${profile.deskHours}",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
        }
      }
    }
  }
}

@Composable
private fun DietPreferenceSelector(
  selectedDiet: DietType,
  onSelectDiet: (DietType) -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Text(
        text = "Primary Diet Preference",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = "Personalizes your daily Indian office nutrition and chai break swaps.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(12.dp))

      DietType.values().forEach { diet ->
        val isSelected = selectedDiet == diet
        Surface(
          color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onSelectDiet(diet) }
            .testTag("select_diet_${diet.name.lowercase()}")
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              DietIndicatorBadge(dietType = diet)
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = diet.displayName,
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                ),
                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
              )
            }
            if (isSelected) {
              Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun DailyGoalsCard(profile: UserProfile) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Text(
        text = "Daily Office Wellness Goals",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )

      Spacer(modifier = Modifier.height(10.dp))

      GoalItemRow(label = "Daily Desk Workout", value = "${profile.dailyWorkoutMinutesGoal} Minutes / Day")
      GoalItemRow(label = "AC Hydration Target", value = "${profile.dailyWaterGoalGlasses} Glasses (2.5L)")
      GoalItemRow(label = "Hourly Stand-Up Walks", value = "${profile.hourlyStandUpGoal} Times / Shift")
    }
  }
}

@Composable
private fun GoalItemRow(label: String, value: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 6.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    Text(text = value, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
  }
}

@Composable
private fun RemindersCard(
  remindersEnabled: Boolean,
  onToggleReminders: (Boolean) -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(18.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Filled.Notifications,
          contentDescription = null,
          tint = EmeraldGreenPrimary,
          modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(
            text = "Hourly Desk Stretch Alerts",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Nudges to drink water & stretch",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      Switch(
        checked = remindersEnabled,
        onCheckedChange = onToggleReminders,
        colors = SwitchDefaults.colors(
          checkedThumbColor = Color.White,
          checkedTrackColor = EmeraldGreenPrimary
        )
      )
    }
  }
}

@Composable
private fun ErgonomicsGuideCard() {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Filled.SelfImprovement,
          contentDescription = null,
          tint = EmeraldGreenPrimary,
          modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Official Desk Ergonomics Checklist",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      val tips = listOf(
        "Monitor Height: Top 1/3rd of screen should be exactly at your eye level.",
        "Elbow Angle: Keep arms bent at 90-100 degrees resting lightly on armrests.",
        "Feet Position: Keep both soles firmly flat on the floor; never cross legs for long.",
        "Lumbar Arch: Use a small lumbar pillow or rolled towel to maintain natural spinal curve.",
        "20-20-20 Rule: Every 20 minutes, gaze at an object 20 feet away for 20 seconds."
      )

      tips.forEach { tip ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
          verticalAlignment = Alignment.Top
        ) {
          Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = null,
            tint = EmeraldGreenPrimary,
            modifier = Modifier
              .size(16.dp)
              .padding(top = 2.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = tip,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }
  }
}

@Composable
private fun EditProfileDialog(
  currentProfile: UserProfile,
  onDismiss: () -> Unit,
  onSave: (UserProfile) -> Unit
) {
  var name by remember { mutableStateOf(currentProfile.name) }
  var role by remember { mutableStateOf(currentProfile.role) }
  var city by remember { mutableStateOf(currentProfile.companyCity) }
  var hours by remember { mutableStateOf(currentProfile.deskHours) }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Text(
        text = "Edit Profile & Shift",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
      )
    },
    text = {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        OutlinedTextField(
          value = name,
          onValueChange = { name = it },
          label = { Text("Your Name") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("edit_name_input")
        )
        OutlinedTextField(
          value = role,
          onValueChange = { role = it },
          label = { Text("Job Role / Designation") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("edit_role_input")
        )
        OutlinedTextField(
          value = city,
          onValueChange = { city = it },
          label = { Text("City & Work Model") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("edit_city_input")
        )
        OutlinedTextField(
          value = hours,
          onValueChange = { hours = it },
          label = { Text("Desk Hours (e.g. 9:00 AM - 6:00 PM)") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("edit_hours_input")
        )
      }
    },
    confirmButton = {
      Button(
        onClick = {
          onSave(
            currentProfile.copy(
              name = name.ifBlank { "Desk Worker" },
              role = role.ifBlank { "Corporate Professional" },
              companyCity = city.ifBlank { "Bengaluru" },
              deskHours = hours.ifBlank { "9:00 AM - 6:00 PM" }
            )
          )
        },
        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
        modifier = Modifier.testTag("save_profile_button")
      ) {
        Text("Save Settings")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  )
}
