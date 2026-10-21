package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
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
import com.example.model.ChaiBreakSwap
import com.example.model.DietType
import com.example.model.MealItem
import com.example.ui.theme.AccentGold
import com.example.ui.theme.CalorieOrange
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.WaterBlue

@Composable
fun MealsScreen(
  selectedDiet: DietType,
  onDietSelected: (DietType) -> Unit,
  waterCount: Int,
  waterGoal: Int,
  onIncrementWater: () -> Unit,
  modifier: Modifier = Modifier
) {
  val allMeals = SampleData.meals.filter { it.dietType == selectedDiet }
  val totalCalories = allMeals.sumOf { it.calories }
  val totalProtein = allMeals.sumOf { it.proteinGrams }
  val totalCarbs = allMeals.sumOf { it.carbsGrams }
  val totalFat = allMeals.sumOf { it.fatGrams }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("meals_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header
    item {
      Column {
        Text(
          text = "Indian Office Meal Plans",
          style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "Tailored for sedentary desk hours with high protein, low glycemic index & zero 3 PM sugar crashes.",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    // 4 Diet Tabs: Vegetarian, Non-Vegetarian, Jain, Eggetarian
    item {
      ScrollableTabRow(
        selectedTabIndex = DietType.values().indexOf(selectedDiet),
        edgePadding = 0.dp,
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(12.dp))
          .testTag("diet_tabs")
      ) {
        DietType.values().forEach { diet ->
          val isSelected = selectedDiet == diet
          Tab(
            selected = isSelected,
            onClick = { onDietSelected(diet) },
            text = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                DietIndicatorBadge(dietType = diet)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = when (diet) {
                    DietType.VEGETARIAN -> "Vegetarian"
                    DietType.NON_VEGETARIAN -> "Non-Veg"
                    DietType.JAIN -> "Jain"
                    DietType.EGGETARIAN -> "Eggetarian"
                  },
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                  color = if (isSelected) EmeraldGreenPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            },
            modifier = Modifier.testTag("diet_tab_${diet.name.lowercase()}")
          )
        }
      }
    }

    // Daily Macro Summary Card
    item {
      MacroSummaryCard(
        dietType = selectedDiet,
        calories = totalCalories,
        protein = totalProtein,
        carbs = totalCarbs,
        fat = totalFat
      )
    }

    // AC Office Hydration Card
    item {
      WaterTrackerCard(
        current = waterCount,
        goal = waterGoal,
        onAdd = onIncrementWater
      )
    }

    // Meal Items Header
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Daily Office Timetable",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "${allMeals.size} Meals",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    // Meal Cards
    items(allMeals, key = { it.id }) { meal ->
      MealItemCard(meal = meal)
    }

    // Section: Smart Indian Chai Time Swaps
    item {
      Text(
        text = "Smart 4 PM Chai Break Swaps",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )
    }

    items(SampleData.chaiSwaps) { swap ->
      ChaiSwapCard(swap = swap)
    }
  }
}

@Composable
fun DietIndicatorBadge(dietType: DietType) {
  when (dietType) {
    DietType.VEGETARIAN -> {
      Box(
        modifier = Modifier
          .size(16.dp)
          .border(1.5.dp, Color(0xFF2E7D32), RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.Center
      ) {
        Box(
          modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(Color(0xFF2E7D32))
        )
      }
    }
    DietType.NON_VEGETARIAN -> {
      Box(
        modifier = Modifier
          .size(16.dp)
          .border(1.5.dp, Color(0xFFC62828), RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.Center
      ) {
        Box(
          modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(Color(0xFFC62828))
        )
      }
    }
    DietType.JAIN -> {
      Box(
        modifier = Modifier
          .size(16.dp)
          .clip(CircleShape)
          .background(Color(0xFFE65100)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Filled.Eco,
          contentDescription = "Jain",
          tint = Color.White,
          modifier = Modifier.size(11.dp)
        )
      }
    }
    DietType.EGGETARIAN -> {
      Box(
        modifier = Modifier
          .size(16.dp)
          .border(1.5.dp, Color(0xFFF9A825), RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.Center
      ) {
        Box(
          modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(Color(0xFFF9A825))
        )
      }
    }
  }
}

@Composable
private fun MacroSummaryCard(
  dietType: DietType,
  calories: Int,
  protein: Int,
  carbs: Int,
  fat: Int
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = dietType.displayName,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Total Daily Desk Energy",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
        Surface(
          color = MaterialTheme.colorScheme.primaryContainer,
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(
            text = "~$calories kcal",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Macros Grid
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        MacroPill(label = "Protein", value = "${protein}g", color = EmeraldGreenPrimary)
        MacroPill(label = "Carbs", value = "${carbs}g", color = CalorieOrange)
        MacroPill(label = "Healthy Fat", value = "${fat}g", color = AccentGold)
      }
    }
  }
}

@Composable
private fun MacroPill(label: String, value: String, color: Color) {
  Surface(
    color = color.copy(alpha = 0.1f),
    shape = RoundedCornerShape(10.dp),
    modifier = Modifier.width(100.dp)
  ) {
    Column(
      modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = value,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = color
      )
      Text(
        text = label,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}

@Composable
private fun WaterTrackerCard(
  current: Int,
  goal: Int,
  onAdd: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(WaterBlue.copy(alpha = 0.15f)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Opacity,
            contentDescription = null,
            tint = WaterBlue,
            modifier = Modifier.size(24.dp)
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(
            text = "AC Room Water Intake",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "$current of $goal glasses logged today (2.5L)",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      Button(
        onClick = onAdd,
        colors = ButtonDefaults.buttonColors(containerColor = WaterBlue),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(imageVector = Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("+1 Glass", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
        }
      }
    }
  }
}

@Composable
private fun MealItemCard(meal: MealItem) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("meal_card_${meal.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          color = MaterialTheme.colorScheme.primaryContainer,
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = meal.timeSlot,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Text(
          text = "${meal.calories} kcal • ${meal.proteinGrams}g Protein",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
          color = EmeraldGreenPrimary
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        DietIndicatorBadge(dietType = meal.dietType)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = meal.title,
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
      }

      Text(
        text = meal.regionalName,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = meal.description,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(10.dp))

      Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Desk Tip: ${meal.officeTip}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }
    }
  }
}

@Composable
private fun ChaiSwapCard(swap: ChaiBreakSwap) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier.weight(1f),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = swap.traditionalItem,
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
              color = MaterialTheme.colorScheme.error
            )
            Text(
              text = "${swap.traditionalCalories} kcal",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        Text(
          text = "➔",
          style = MaterialTheme.typography.titleMedium,
          color = EmeraldGreenPrimary,
          modifier = Modifier.padding(horizontal = 6.dp)
        )

        Row(
          modifier = Modifier.weight(1.3f),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = swap.smartSwap,
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
              color = EmeraldGreenPrimary
            )
            Text(
              text = "${swap.smartCalories} kcal • Saves ${swap.caloriesSaved} kcal",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
              color = AccentGold
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = swap.healthyBenefit,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}
