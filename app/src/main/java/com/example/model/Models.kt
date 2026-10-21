package com.example.model

enum class DietType(val displayName: String, val badgeColor: Long) {
  VEGETARIAN("Vegetarian (शाकाहारी)", 0xFF2E7D32),
  NON_VEGETARIAN("Non-Vegetarian", 0xFFC62828),
  JAIN("Jain (No Root Veg)", 0xFFE65100),
  EGGETARIAN("Eggetarian", 0xFFF9A825)
}

enum class WorkoutCategory(val title: String) {
  ALL("All"),
  POSTURE("Posture Fix"),
  CHAIR_YOGA("Chair Yoga"),
  ACTIVE_BURN("Chai Break Burn"),
  LUMBAR_RELIEF("Back & Sciatica")
}

data class ExerciseStep(
  val id: String,
  val name: String,
  val durationSeconds: Int,
  val targetArea: String,
  val instructions: String,
  val ergonomicTip: String
)

data class WorkoutRoutine(
  val id: String,
  val title: String,
  val subtitle: String,
  val durationMinutes: Int = 10,
  val caloriesBurned: Int,
  val category: WorkoutCategory,
  val intensity: String, // "Gentle", "Moderate", "Energizing"
  val benefits: String,
  val steps: List<ExerciseStep>
)

data class MealItem(
  val id: String,
  val dietType: DietType,
  val timeSlot: String, // "Breakfast (8:30 AM)", "Office Lunch (1:30 PM)", "Chai Break (4:30 PM)", "Light Dinner (8:00 PM)"
  val title: String,
  val regionalName: String,
  val description: String,
  val calories: Int,
  val proteinGrams: Int,
  val carbsGrams: Int,
  val fatGrams: Int,
  val officeTip: String
)

data class ChaiBreakSwap(
  val traditionalItem: String,
  val traditionalCalories: Int,
  val smartSwap: String,
  val smartCalories: Int,
  val caloriesSaved: Int,
  val healthyBenefit: String
)

data class DailyHabit(
  val id: String,
  val title: String,
  val subtitle: String,
  val isCompleted: Boolean = false,
  val category: String
)

data class BadgeItem(
  val id: String,
  val title: String,
  val description: String,
  val isUnlocked: Boolean,
  val dateUnlocked: String? = null
)

data class UserProfile(
  val name: String = "Rahul Sharma",
  val role: String = "Senior Software Engineer",
  val companyCity: String = "Bengaluru (WFH / Hybrid)",
  val deskHours: String = "9:30 AM - 6:30 PM",
  val primaryDiet: DietType = DietType.VEGETARIAN,
  val dailyWaterGoalGlasses: Int = 8,
  val dailyWorkoutMinutesGoal: Int = 10,
  val hourlyStandUpGoal: Int = 6,
  val remindersEnabled: Boolean = true
)
