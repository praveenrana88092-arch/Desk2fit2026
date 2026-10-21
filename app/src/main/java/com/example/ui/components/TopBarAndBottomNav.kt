package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentGold
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.viewmodel.ScreenTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Desk2FitTopBar(
  streakCount: Int,
  modifier: Modifier = Modifier
) {
  TopAppBar(
    title = {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Box(
          modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(EmeraldGreenPrimary),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.SelfImprovement,
            contentDescription = "Desk2Fit Logo",
            tint = Color.White,
            modifier = Modifier.size(26.dp)
          )
        }
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "Desk2Fit",
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
              ),
              color = MaterialTheme.colorScheme.onSurface
            )
            Surface(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.padding(start = 6.dp)
            ) {
              Text(
                text = "INDIA",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
              )
            }
          }
          Text(
            text = "Office Wellness & 10-Min Workouts",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    },
    actions = {
      // Streak Pill
      Surface(
        color = AccentGold.copy(alpha = 0.15f),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
          .padding(end = 16.dp)
          .testTag("top_bar_streak_pill")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            imageVector = Icons.Filled.LocalFireDepartment,
            contentDescription = "Streak Flame",
            tint = AccentGold,
            modifier = Modifier.size(18.dp)
          )
          Text(
            text = "$streakCount Days",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    modifier = modifier.testTag("desk2fit_top_bar")
  )
}

@Composable
fun Desk2FitBottomNavBar(
  currentTab: ScreenTab,
  onTabSelected: (ScreenTab) -> Unit,
  modifier: Modifier = Modifier
) {
  NavigationBar(
    containerColor = MaterialTheme.colorScheme.surface,
    tonalElevation = 8.dp,
    modifier = modifier.testTag("bottom_nav_bar")
  ) {
    ScreenTab.values().forEach { tab ->
      val selected = currentTab == tab
      val icon = when (tab) {
        ScreenTab.DASHBOARD -> if (selected) Icons.Filled.Home else Icons.Outlined.Home
        ScreenTab.WORKOUTS -> if (selected) Icons.Filled.FitnessCenter else Icons.Outlined.FitnessCenter
        ScreenTab.MEALS -> if (selected) Icons.Filled.Restaurant else Icons.Outlined.Restaurant
        ScreenTab.PROGRESS -> if (selected) Icons.Filled.BarChart else Icons.Outlined.BarChart
        ScreenTab.PROFILE -> if (selected) Icons.Filled.Person else Icons.Outlined.Person
      }

      NavigationBarItem(
        selected = selected,
        onClick = { onTabSelected(tab) },
        icon = {
          Icon(
            imageVector = icon,
            contentDescription = tab.title,
            modifier = Modifier.size(24.dp)
          )
        },
        label = {
          Text(
            text = tab.title,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
          selectedTextColor = MaterialTheme.colorScheme.primary,
          indicatorColor = MaterialTheme.colorScheme.primaryContainer,
          unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
          unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
      )
    }
  }
}
