package com.example.data

import com.example.model.BadgeItem
import com.example.model.ChaiBreakSwap
import com.example.model.DailyHabit
import com.example.model.DietType
import com.example.model.ExerciseStep
import com.example.model.MealItem
import com.example.model.WorkoutCategory
import com.example.model.WorkoutRoutine

object SampleData {

  val routines = listOf(
    WorkoutRoutine(
      id = "posture_reset",
      title = "10-Min Posture Reset",
      subtitle = "Combat slouching & 'tech-neck' from long screen hours",
      durationMinutes = 10,
      caloriesBurned = 45,
      category = WorkoutCategory.POSTURE,
      intensity = "Gentle & Relieving",
      benefits = "Realigns cervical spine, opens tight pectoral muscles & reduces shoulder knots",
      steps = listOf(
        ExerciseStep(
          id = "p1",
          name = "Cervical Retraction (Chin Tucks)",
          durationSeconds = 60,
          targetArea = "Neck & Cervical Spine",
          instructions = "Sit upright. Gently pull your chin straight back as if making a double chin. Hold for 5 seconds, relax, and repeat 10 times.",
          ergonomicTip = "Do not tilt your head down; slide your head horizontally backward."
        ),
        ExerciseStep(
          id = "p2",
          name = "Shoulder Blade Scapular Squeezes",
          durationSeconds = 60,
          targetArea = "Upper Back & Trapezius",
          instructions = "Inhale and pull your shoulder blades down and back together. Imagine holding a pen between your shoulder blades.",
          ergonomicTip = "Keep shoulders away from your ears to prevent trap tension."
        ),
        ExerciseStep(
          id = "p3",
          name = "Desk Chest & Pectoral Opener",
          durationSeconds = 60,
          targetArea = "Chest & Front Deltoids",
          instructions = "Interlace your hands behind your lower back or hold the back of your chair. Gently push your chest outward and lift chin slightly.",
          ergonomicTip = "Counteracts hours of forward hunched keyboard posture."
        ),
        ExerciseStep(
          id = "p4",
          name = "Seated Spinal Twist (Ardha Matsyendrasana)",
          durationSeconds = 60,
          targetArea = "Thoracic Spine & Obliques",
          instructions = "Place your right hand on your left chair armrest. Inhale to lengthen your spine, exhale and gently twist left. Switch sides at 30 seconds.",
          ergonomicTip = "Twist from the middle back, keeping your hips square on the seat."
        ),
        ExerciseStep(
          id = "p5",
          name = "Keyboard Carpal Tunnel & Wrist Flex",
          durationSeconds = 60,
          targetArea = "Forearms, Wrists & Fingers",
          instructions = "Extend arm forward, palm facing up. Pull fingers gently downward with opposite hand for 15s. Reverse palm facing down. Repeat on other wrist.",
          ergonomicTip = "Prevents repetitive strain injury (RSI) from mouse & typing."
        ),
        ExerciseStep(
          id = "p6",
          name = "Seated Chair Cat-Cow",
          durationSeconds = 60,
          targetArea = "Entire Spine & Core",
          instructions = "Rest hands on knees. Inhale, arch your back and gaze upward (Cow). Exhale, round your spine and tuck your chin toward your chest (Cat).",
          ergonomicTip = "Coordinates your breath with spinal fluid circulation."
        ),
        ExerciseStep(
          id = "p7",
          name = "Overhead Lateral Side Stretch",
          durationSeconds = 60,
          targetArea = "Lats, Intercostals & Ribcage",
          instructions = "Raise right arm overhead, lean gently toward the left side. Feel deep expansion along your ribcage. Switch sides at 30 seconds.",
          ergonomicTip = "Expands lung capacity after shallow breathing during stressful meetings."
        ),
        ExerciseStep(
          id = "p8",
          name = "Upper Trapezius Neck Release",
          durationSeconds = 60,
          targetArea = "Side Neck & Levator Scapulae",
          instructions = "Drop right ear toward right shoulder. Use right hand for very gentle pressure. Hold left hand down to anchor. Switch at 30 seconds.",
          ergonomicTip = "Relieves the headache-inducing stiffness from screen glare."
        ),
        ExerciseStep(
          id = "p9",
          name = "Seated Glute & Piriformis Release",
          durationSeconds = 60,
          targetArea = "Glutes & Lower Back",
          instructions = "Cross right ankle over left knee in a 'Figure 4'. Keep back straight and lean torso slightly forward until gentle stretch is felt. Switch at 30s.",
          ergonomicTip = "Releases pressure on the sciatic nerve caused by firm office chairs."
        ),
        ExerciseStep(
          id = "p10",
          name = "20-20-20 Eye Palming & Deep Breath",
          durationSeconds = 60,
          targetArea = "Optic Nerve & Mind",
          instructions = "Rub palms together vigorously until warm. Cup warm palms gently over closed eyes. Inhale deeply for 4 counts, exhale for 6 counts.",
          ergonomicTip = "Gives your optic nerve complete darkness, relieving blue-light fatigue."
        )
      )
    ),
    WorkoutRoutine(
      id = "chai_break_burn",
      title = "10-Min Chai Break Burn",
      subtitle = "Quick active energy boost between meetings",
      durationMinutes = 10,
      caloriesBurned = 68,
      category = WorkoutCategory.ACTIVE_BURN,
      intensity = "Energizing & High Calorie",
      benefits = "Spikes metabolism, prevents post-lunch 3 PM slump & boosts circulation",
      steps = listOf(
        ExerciseStep(
          id = "c1",
          name = "Desk Incline Push-Ups",
          durationSeconds = 60,
          targetArea = "Chest, Triceps & Core",
          instructions = "Place hands shoulder-width apart on the sturdy edge of your desk. Step back into a firm plank. Lower chest toward desk and press back up.",
          ergonomicTip = "Ensure your desk or table does not roll or slide."
        ),
        ExerciseStep(
          id = "c2",
          name = "Chair Squats / Tap & Rise",
          durationSeconds = 60,
          targetArea = "Quadriceps, Glutes & Hamstrings",
          instructions = "Stand in front of your chair, feet hip-width. Push hips back, lightly graze the seat cushion with your glutes, then drive through heels to stand.",
          ergonomicTip = "Keep your knees tracking over your toes, chest held proud."
        ),
        ExerciseStep(
          id = "c3",
          name = "Standing Calf Raises with Desk Support",
          durationSeconds = 60,
          targetArea = "Calves & Ankle Mobility",
          instructions = "Hold desk for balance. Rise high onto balls of feet, squeeze calves at the peak for 2 seconds, then lower down with control.",
          ergonomicTip = "Calves act as the 'second heart' pumping pooled venous blood back up."
        ),
        ExerciseStep(
          id = "c4",
          name = "Seated Knee Tucks (Core Activator)",
          durationSeconds = 60,
          targetArea = "Lower Abdominals & Hip Flexors",
          instructions = "Sit on edge of chair holding seat sides. Lean back slightly, lift both knees toward chest, then lower feet without touching floor.",
          ergonomicTip = "Maintain a flat lower back; don't slump into the chair."
        ),
        ExerciseStep(
          id = "c5",
          name = "Silent Desk Marching in Place",
          durationSeconds = 60,
          targetArea = "Cardiovascular & Hip Flexors",
          instructions = "Stand tall next to your desk. Lift knees high toward waist level at a steady, rhythmic cadence without making shoe noise on office floor.",
          ergonomicTip = "Pump your arms naturally to elevate heart rate safely."
        ),
        ExerciseStep(
          id = "c6",
          name = "Chair Dips for Triceps",
          durationSeconds = 60,
          targetArea = "Triceps & Anterior Shoulders",
          instructions = "Grip the front edge of a locked chair. Slide hips just off the seat. Bend elbows to 90 degrees, then push through palms to straighten.",
          ergonomicTip = "Keep elbows pointing straight back, not flared out."
        ),
        ExerciseStep(
          id = "c7",
          name = "Standing Torso Windmills",
          durationSeconds = 60,
          targetArea = "Hamstrings, Obliques & Lower Back",
          instructions = "Stand wide with arms extended parallel to floor. Rotate torso to tap opposite knee or shin with right hand, stand, and repeat left.",
          ergonomicTip = "Hinge from the hips with a soft bend in the knees."
        ),
        ExerciseStep(
          id = "c8",
          name = "Glute Pulse Kickbacks",
          durationSeconds = 60,
          targetArea = "Gluteus Maximus",
          instructions = "Rest hands on desk. Squeeze right glute and pulse right leg backward for 30s. Switch to left leg for the remaining 30s.",
          ergonomicTip = "Wakes up dormant glutes turned off by prolonged sitting ('dead butt syndrome')."
        ),
        ExerciseStep(
          id = "c9",
          name = "Seated Jumping Jacks (Low Impact)",
          durationSeconds = 60,
          targetArea = "Full Body Coordination",
          instructions = "Sit upright. Simultaneously extend arms overhead and kick legs outward in a jack motion, then return to center. Repeat briskly.",
          ergonomicTip = "Office-friendly: 0 floor vibration for colleagues sitting nearby!"
        ),
        ExerciseStep(
          id = "c10",
          name = "Standing Quad Pull & Shakeout",
          durationSeconds = 60,
          targetArea = "Quadriceps & Hip Flexors",
          instructions = "Hold desk with left hand. Grasp right ankle with right hand, pulling heel toward glute. Keep knees together. Switch sides at 30 seconds.",
          ergonomicTip = "Deeply opens tight hip flexors compressed from long sedentary hours."
        )
      )
    ),
    WorkoutRoutine(
      id = "chair_yoga",
      title = "10-Min Chair Yoga & Zen",
      subtitle = "De-stress before or after high-pressure calls",
      durationMinutes = 10,
      caloriesBurned = 38,
      category = WorkoutCategory.CHAIR_YOGA,
      intensity = "Calming & Meditative",
      benefits = "Activates parasympathetic nervous system, lowers cortisol & centers the mind",
      steps = listOf(
        ExerciseStep(
          id = "y1",
          name = "Desk Sukhasana (Seated Center)",
          durationSeconds = 60,
          targetArea = "Mind & Core Alignment",
          instructions = "Sit tall with feet flat on the floor, hands in Gyan Mudra on your lap. Close your eyes and take 5 deep belly breaths.",
          ergonomicTip = "Feel your spine growing taller with every inhalation."
        ),
        ExerciseStep(
          id = "y2",
          name = "Garudasana (Eagle Arms)",
          durationSeconds = 60,
          targetArea = "Upper Trapezius & Rhomboids",
          instructions = "Cross left elbow under right elbow, wrap forearms and press palms together. Lift elbows to shoulder height. Switch sides at 30s.",
          ergonomicTip = "Breathe deeply into the space between your shoulder blades."
        ),
        ExerciseStep(
          id = "y3",
          name = "Chair Virabhadrasana (Seated Warrior II)",
          durationSeconds = 60,
          targetArea = "Groin, Hips & Chest",
          instructions = "Turn sideways on chair, step back leg straight with foot grounded. Extend arms wide parallel to floor. Switch at 30s.",
          ergonomicTip = "Keep shoulders stacked directly over hips."
        ),
        ExerciseStep(
          id = "y4",
          name = "Seated Pigeon Pose (Kapotasana)",
          durationSeconds = 60,
          targetArea = "Hip Rotators & Gluteus Medius",
          instructions = "Rest right ankle on left thigh just above knee. Flex right foot. Lengthen spine and hinge slightly forward. Switch at 30s.",
          ergonomicTip = "Never force the knee; let gravity open the hip naturally."
        ),
        ExerciseStep(
          id = "y5",
          name = "Cactus Arms Heart Opener",
          durationSeconds = 60,
          targetArea = "Chest & Thoracic Spine",
          instructions = "Bring arms into 90-degree 'goalpost' cactus shape. Inhale, draw elbows slightly backward, lift heart toward the ceiling.",
          ergonomicTip = "Counteracts the hunched 'coder slumping' habit."
        ),
        ExerciseStep(
          id = "y6",
          name = "Chair Forward Fold (Uttanasana)",
          durationSeconds = 60,
          targetArea = "Hamstrings, Sacrum & Neck",
          instructions = "Separate knees slightly. Fold forward from hips, draping your torso over thighs and letting head hang heavy toward the floor.",
          ergonomicTip = "Releases all accumulated compression from the lumbar discs."
        ),
        ExerciseStep(
          id = "y7",
          name = "Wrist & Finger Mudra Release",
          durationSeconds = 60,
          targetArea = "Fingers & Meridians",
          instructions = "Press palms together at chest in Namaste. Gently lower wrists down while keeping palms pressed. Then open and close fingers wide.",
          ergonomicTip = "Stimulates acupressure points in the palms."
        ),
        ExerciseStep(
          id = "y8",
          name = "Seated Crescent Moon Stretch",
          durationSeconds = 60,
          targetArea = "Obliques & Quadratus Lumborum",
          instructions = "Interlock fingers overhead, index fingers pointing up. Lean gently to the right for 30s, then lean to the left for 30s.",
          ergonomicTip = "Keep both sit-bones firmly rooted into the chair seat."
        ),
        ExerciseStep(
          id = "y9",
          name = "Anulom Vilom (Alternate Nostril Pranayama)",
          durationSeconds = 60,
          targetArea = "Nervous System & Mental Focus",
          instructions = "Use thumb to close right nostril, inhale left. Close left with ring finger, exhale right. Inhale right, exhale left. Slow rhythmic breath.",
          ergonomicTip = "Balances left and right brain hemispheres, reducing meeting fatigue."
        ),
        ExerciseStep(
          id = "y10",
          name = "Silent Gratitude Shavasana in Chair",
          durationSeconds = 60,
          targetArea = "Full Body Rejuvenation",
          instructions = "Lean back comfortably into your chair. Relax your jaw, unclench your teeth, soften your forehead. Soak in peaceful stillness.",
          ergonomicTip = "A 60-second mental reset is more restorative than scrolling your phone."
        )
      )
    ),
    WorkoutRoutine(
      id = "lumbar_relief",
      title = "10-Min Lumbar & Sciatica Relief",
      subtitle = "Soothe lower back stiffness & tight glutes",
      durationMinutes = 10,
      caloriesBurned = 40,
      category = WorkoutCategory.LUMBAR_RELIEF,
      intensity = "Gentle & Therapeutic",
      benefits = "Decompresses L4-L5 lumbar vertebrae & relaxes contracted piriformis",
      steps = listOf(
        ExerciseStep(
          id = "l1",
          name = "Seated Pelvic Rocking",
          durationSeconds = 60,
          targetArea = "Pelvis & Sacroiliac Joint",
          instructions = "Sit upright. Tilt your pelvis forward to arch lower back, then tuck your tailbone under to flatten lower back against chair back.",
          ergonomicTip = "Gentle continuous rocking pumps nutrient-rich fluid into spinal discs."
        ),
        ExerciseStep(
          id = "l2",
          name = "Single Knee Hug to Chest",
          durationSeconds = 60,
          targetArea = "Lower Back & Hip Joint",
          instructions = "Interlock fingers around your right shin. Gently hug knee up toward your chest while keeping spine straight. Switch sides at 30 seconds.",
          ergonomicTip = "Avoid rounding your upper back; keep your chest proud."
        ),
        ExerciseStep(
          id = "l3",
          name = "Chair Hamstring Stretch",
          durationSeconds = 60,
          targetArea = "Hamstrings (Which Pull Lower Back)",
          instructions = "Extend right leg forward with heel on floor and toes pointing up. Hinge forward from hips with a flat back. Switch at 30 seconds.",
          ergonomicTip = "Tight hamstrings are the #1 hidden cause of office lower back pain."
        ),
        ExerciseStep(
          id = "l4",
          name = "Standing Lumbar Arch & Extension",
          durationSeconds = 60,
          targetArea = "Lumbar Lordosis Restoration",
          instructions = "Stand up. Place hands on your lower back/sacrum with fingers pointing down. Gently arch backward while engaging glutes.",
          ergonomicTip = "Directly reverses the forward flexion from prolonged sitting."
        ),
        ExerciseStep(
          id = "l5",
          name = "Seated Piriformis Cross-Leg Lean",
          durationSeconds = 60,
          targetArea = "Deep Gluteal & Sciatic Pathway",
          instructions = "Cross right knee over left knee tightly. Gently hug top knee toward opposite shoulder and sit tall. Switch sides at 30 seconds.",
          ergonomicTip = "Releases the piriformis muscle that pinches the sciatic nerve."
        ),
        ExerciseStep(
          id = "l6",
          name = "Desk-Supported Forward Lunge",
          durationSeconds = 60,
          targetArea = "Iliopsoas & Hip Flexors",
          instructions = "Hold desk for stability. Step left foot back into a shallow lunge. Tuck tailbone under and squeeze left glute. Switch at 30 seconds.",
          ergonomicTip = "Opens the psoas muscle which shortens from 8+ hours of sitting."
        ),
        ExerciseStep(
          id = "l7",
          name = "Chair Lateral Torso Sweep",
          durationSeconds = 60,
          targetArea = "Quadratus Lumborum (QL Muscle)",
          instructions = "Hold left chair arm with right hand across body. Reach left arm up and curve torso over to the right. Switch sides at 30 seconds.",
          ergonomicTip = "Lengthens the deep lower back muscle prone to spasms."
        ),
        ExerciseStep(
          id = "l8",
          name = "Standing Calf & Achilles Wall Press",
          durationSeconds = 60,
          targetArea = "Gastrocnemius & Plantar Fascia",
          instructions = "Place hands against wall or tall desk. Step right leg back with heel firmly planted into floor. Lean forward. Switch at 30 seconds.",
          ergonomicTip = "Promotes venous blood return from lower legs back to heart."
        ),
        ExerciseStep(
          id = "l9",
          name = "Seated Inner Thigh Butterfly (Baddha Konasana)",
          durationSeconds = 60,
          targetArea = "Adductors & Groin",
          instructions = "Sit on chair edge. Bring soles of feet together with knees opening wide. Gently rest hands on knees and breathe smoothly.",
          ergonomicTip = "Releases pelvic floor tension caused by continuous work stress."
        ),
        ExerciseStep(
          id = "l10",
          name = "Supported Posture Check & Re-alignment",
          durationSeconds = 60,
          targetArea = "Whole Body Posture",
          instructions = "Sit all the way back into chair. Ensure lower back touches lumbar cushion, feet flat, elbows at 90 degrees, shoulders relaxed.",
          ergonomicTip = "Lock this optimal ergonomic posture in muscle memory for the rest of your shift!"
        )
      )
    )
  )

  val meals = listOf(
    // VEGETARIAN
    MealItem(
      id = "veg_1",
      dietType = DietType.VEGETARIAN,
      timeSlot = "Breakfast (8:30 AM)",
      title = "Moong Dal Cheela with Mint Chutney",
      regionalName = "मूँग दाल चीला",
      description = "2 golden protein-packed yellow moong crepes stuffed with grated paneer, fresh coriander, ginger and green chillies.",
      calories = 310,
      proteinGrams = 16,
      carbsGrams = 34,
      fatGrams = 9,
      officeTip = "High protein prevents mid-morning energy dips during sprint meetings."
    ),
    MealItem(
      id = "veg_2",
      dietType = DietType.VEGETARIAN,
      timeSlot = "Office Dabba / Lunch (1:30 PM)",
      title = "Paneer Bhurji + 2 Multigrain Rotis + Dal Tadka",
      regionalName = "पनीर भुर्जी और मल्टीग्रेन रोटी",
      description = "Cottage cheese scrambled with onions, capsicum, tomatoes, served with wholewheat-ragi phulkas, bowl of yellow dal and cucumber slices.",
      calories = 520,
      proteinGrams = 26,
      carbsGrams = 52,
      fatGrams = 18,
      officeTip = "Multigrain fiber slows digestion, preventing the heavy post-lunch food coma."
    ),
    MealItem(
      id = "veg_3",
      dietType = DietType.VEGETARIAN,
      timeSlot = "Chai Break (4:30 PM)",
      title = "Roasted Makhana (Foxnuts) + Ginger Elaichi Tea",
      regionalName = "भुना मखाना और अदरक वाली चाय",
      description = "Crunchy roasted lotus seeds tossed in 1/2 tsp ghee, turmeric, rock salt, paired with fresh adrak-elaichi chai (stevia or 1/2 spoon jaggery).",
      calories = 145,
      proteinGrams = 5,
      carbsGrams = 22,
      fatGrams = 4,
      officeTip = "Saves ~200 calories compared to oily office canteen samosas or puffs."
    ),
    MealItem(
      id = "veg_4",
      dietType = DietType.VEGETARIAN,
      timeSlot = "Light Dinner (8:00 PM)",
      title = "Moong-Rice Khichdi with Ghee + Lauki Sabzi + Chaas",
      regionalName = "दाल खिचड़ी, लौकी सब्ज़ी और छाछ",
      description = "Soothing comforting khichdi seasoned with cumin and hing, tender bottle gourd sabzi, and a chilled glass of roasted jeera buttermilk.",
      calories = 390,
      proteinGrams = 15,
      carbsGrams = 58,
      fatGrams = 9,
      officeTip = "Light on stomach so you fall asleep faster after long screen exposure."
    ),

    // NON-VEGETARIAN
    MealItem(
      id = "nonveg_1",
      dietType = DietType.NON_VEGETARIAN,
      timeSlot = "Breakfast (8:30 AM)",
      title = "2 Boiled Eggs + Wholewheat Toast + Green Tea",
      regionalName = "उबले अंडे और टोस्ट",
      description = "2 farm-fresh boiled eggs seasoned with black pepper & chaat masala, whole grain sourdough toast, and anti-oxidant rich green tea.",
      calories = 290,
      proteinGrams = 19,
      carbsGrams = 24,
      fatGrams = 11,
      officeTip = "Choline in egg yolks sharpens cognitive focus and memory retention."
    ),
    MealItem(
      id = "nonveg_2",
      dietType = DietType.NON_VEGETARIAN,
      timeSlot = "Office Dabba / Lunch (1:30 PM)",
      title = "Grilled Chicken Tikka Wrap (Wholewheat Roti)",
      regionalName = "चिकन टिक्का रैप",
      description = "Marinated tender tandoori chicken breast slices rolled in a fresh wholewheat roti with crunchy bell peppers, onions and mint Greek yogurt sauce.",
      calories = 490,
      proteinGrams = 36,
      carbsGrams = 42,
      fatGrams = 12,
      officeTip = "Easy to eat at your desk during busy deadlines without oily mess."
    ),
    MealItem(
      id = "nonveg_3",
      dietType = DietType.NON_VEGETARIAN,
      timeSlot = "Chai Break (4:30 PM)",
      title = "Boiled Egg Chaat + Masala Chai",
      regionalName = "अंडा चाट और मसाला चाय",
      description = "Diced hard-boiled eggs tossed with chopped green chillies, coriander, lemon squeeze, chaat masala, paired with sugar-free masala chai.",
      calories = 165,
      proteinGrams = 13,
      carbsGrams = 6,
      fatGrams = 10,
      officeTip = "Zero sugar crash; high protein keeps you satiated till dinner."
    ),
    MealItem(
      id = "nonveg_4",
      dietType = DietType.NON_VEGETARIAN,
      timeSlot = "Light Dinner (8:00 PM)",
      title = "Steamed Fish Curry / Chicken with Brown Rice",
      regionalName = "मछली करी और ब्राउन राइस",
      description = "Omega-3 rich fish or lean chicken simmered in light tomato-curry leaf gravy, served with 1 cup brown rice and fresh kachumber salad.",
      calories = 440,
      proteinGrams = 33,
      carbsGrams = 48,
      fatGrams = 9,
      officeTip = "Omega-3 fatty acids lubricate dry eyes strained by monitors."
    ),

    // JAIN
    MealItem(
      id = "jain_1",
      dietType = DietType.JAIN,
      timeSlot = "Breakfast (8:30 AM)",
      title = "Kanda-Free Poha with Green Peas & Peanuts",
      regionalName = "जैन पोहा (मटर और मूँगफली)",
      description = "Flattened rice tempered with mustard seeds, fresh curry leaves, crisp peanuts, sweet green peas, turmeric and a splash of fresh lemon juice.",
      calories = 295,
      proteinGrams = 8,
      carbsGrams = 44,
      fatGrams = 9,
      officeTip = "Easy on digestion, 100% strictly Jain compliant."
    ),
    MealItem(
      id = "jain_2",
      dietType = DietType.JAIN,
      timeSlot = "Office Dabba / Lunch (1:30 PM)",
      title = "2 Phulkas + Jain Paneer Shimla Mirch + Dal Fry",
      regionalName = "जैन पनीर शिमला मिर्च और फुल्का",
      description = "Paneer cubes sautéed with green bell peppers and tomato-cumin gravy (strictly zero onion, garlic or potato), yellow dal, cucumber slices.",
      calories = 510,
      proteinGrams = 23,
      carbsGrams = 54,
      fatGrams = 16,
      officeTip = "Bell peppers provide high Vitamin C to boost immunity in AC offices."
    ),
    MealItem(
      id = "jain_3",
      dietType = DietType.JAIN,
      timeSlot = "Chai Break (4:30 PM)",
      title = "Roasted Chana & Badam + Elaichi Tea",
      regionalName = "भुना चना और इलायची चाय",
      description = "Crisp roasted Bengal gram and 6 almonds with rock salt, paired with aromatic cardamom milk tea made with jaggery or low sugar.",
      calories = 170,
      proteinGrams = 7,
      carbsGrams = 18,
      fatGrams = 7,
      officeTip = "Roasted chana has low glycemic index, keeping blood sugar stable."
    ),
    MealItem(
      id = "jain_4",
      dietType = DietType.JAIN,
      timeSlot = "Light Dinner (8:00 PM)",
      title = "Jain Moong Dal Khichdi + Raw Banana Cutlet + Buttermilk",
      regionalName = "जैन मूंग दाल खिचड़ी और कच्चे केले की टिक्की",
      description = "Simple soothing moong dal khichdi with desi ghee, shallow-roasted raw banana & mint cutlet, accompanied by cumin chaas.",
      calories = 410,
      proteinGrams = 16,
      carbsGrams = 62,
      fatGrams = 10,
      officeTip = "Raw banana is a fantastic prebiotic that supports healthy gut microbiome."
    ),

    // EGGETARIAN
    MealItem(
      id = "egg_1",
      dietType = DietType.EGGETARIAN,
      timeSlot = "Breakfast (8:30 AM)",
      title = "2-Egg Masala Omelette with Oats Toast",
      regionalName = "मसाला आमलेट और ओट्स टोस्ट",
      description = "Fluffy 2-egg omelette with chopped onions, tomatoes, green chillies and fresh coriander, served with toasted oats bread slice.",
      calories = 320,
      proteinGrams = 20,
      carbsGrams = 22,
      fatGrams = 14,
      officeTip = "High-satiety start that curbs morning craving for office biscuits."
    ),
    MealItem(
      id = "egg_2",
      dietType = DietType.EGGETARIAN,
      timeSlot = "Office Dabba / Lunch (1:30 PM)",
      title = "Egg Bhurji (3 Whites, 1 Yolk) + 2 Rotis + Dal",
      regionalName = "अंडा भुर्जी और रोटी",
      description = "Indian spiced scrambled eggs with cumin and spices, 2 warm wholewheat rotis, side of yellow arhar dal, and crisp onion-cucumber salad.",
      calories = 480,
      proteinGrams = 27,
      carbsGrams = 49,
      fatGrams = 14,
      officeTip = "Lean protein blend keeps insulin flat and mind sharp for meetings."
    ),
    MealItem(
      id = "egg_3",
      dietType = DietType.EGGETARIAN,
      timeSlot = "Chai Break (4:30 PM)",
      title = "Hard Boiled Egg White Chaat + Ginger Tea",
      regionalName = "उबला अंडा सफेदी चाट और अदरक चाय",
      description = "3 boiled egg whites cut into cubes, sprinkled with chaat masala, lemon and roasted cumin, paired with refreshing hot adrak chai.",
      calories = 135,
      proteinGrams = 12,
      carbsGrams = 4,
      fatGrams = 1,
      officeTip = "Almost pure protein snack to repair muscle tissues after desk stretches."
    ),
    MealItem(
      id = "egg_4",
      dietType = DietType.EGGETARIAN,
      timeSlot = "Light Dinner (8:00 PM)",
      title = "Home-style Egg Curry with Jeera Rice",
      regionalName = "अंडा करी और जीरा राइस",
      description = "2 boiled eggs in a light aromatic tomato-coriander gravy, served with 1 cup steamed jeera basmati rice and cooling cucumber raita.",
      calories = 430,
      proteinGrams = 21,
      carbsGrams = 52,
      fatGrams = 13,
      officeTip = "Light on tummy, comfortable to digest before bed."
    )
  )

  val chaiSwaps = listOf(
    ChaiBreakSwap(
      traditionalItem = "Haldiram Samosa (1 pc)",
      traditionalCalories = 280,
      smartSwap = "Roasted Spiced Makhana (1 bowl)",
      smartCalories = 90,
      caloriesSaved = 190,
      healthyBenefit = "Zero deep-fried trans fat, loaded with calcium & magnesium for bone density."
    ),
    ChaiBreakSwap(
      traditionalItem = "Canteen Cutting Chai (2 tsp sugar)",
      traditionalCalories = 95,
      smartSwap = "Adrak Chai with Stevia / Cinnamon",
      smartCalories = 25,
      caloriesSaved = 70,
      healthyBenefit = "Prevents insulin spike and avoids the dreaded 4:45 PM energy crash."
    ),
    ChaiBreakSwap(
      traditionalItem = "Bikaji Bhujia / Sev (1 packet)",
      traditionalCalories = 290,
      smartSwap = "Roasted Chana & Kurmura Mix",
      smartCalories = 110,
      caloriesSaved = 180,
      healthyBenefit = "Provides slow-release complex carbs and keeps blood pressure in check."
    ),
    ChaiBreakSwap(
      traditionalItem = "Canteen Medu Vada (2 pcs)",
      traditionalCalories = 330,
      smartSwap = "Steamed Moong Idlis (2 pcs) with Podi",
      smartCalories = 130,
      caloriesSaved = 200,
      healthyBenefit = "Fermented steamed food promotes great digestive health in AC offices."
    )
  )

  val defaultHabits = listOf(
    DailyHabit(
      id = "h1",
      title = "10-Min Desk Workout Done",
      subtitle = "Completed posture or chai break routine",
      isCompleted = true,
      category = "Fitness"
    ),
    DailyHabit(
      id = "h2",
      title = "AC Room Hydration (8 Glasses)",
      subtitle = "Air conditioning dehydrates fast; aim for 2.5L",
      isCompleted = false,
      category = "Hydration"
    ),
    DailyHabit(
      id = "h3",
      title = "Hourly Stand-Up & Walk (6 times)",
      subtitle = "Stand up and walk 2 minutes every hour",
      isCompleted = false,
      category = "Mobility"
    ),
    DailyHabit(
      id = "h4",
      title = "Healthy 4 PM Chai Swap",
      subtitle = "Chose makhana / chana instead of fried samosa",
      isCompleted = true,
      category = "Nutrition"
    ),
    DailyHabit(
      id = "h5",
      title = "20-20-20 Screen Eye Break",
      subtitle = "Look 20 feet away for 20 seconds every 20 mins",
      isCompleted = false,
      category = "Ergonomics"
    )
  )

  val defaultBadges = listOf(
    BadgeItem(
      id = "b1",
      title = "Desk Warrior",
      description = "Completed first 10-min desk routine",
      isUnlocked = true,
      dateUnlocked = "3 days ago"
    ),
    BadgeItem(
      id = "b2",
      title = "5-Day Streak Flame",
      description = "Maintained office health streak for 5 days in a row",
      isUnlocked = true,
      dateUnlocked = "Today!"
    ),
    BadgeItem(
      id = "b3",
      title = "Chai Swap Champion",
      description = "Chose healthy makhana/chana swap 5 times",
      isUnlocked = true,
      dateUnlocked = "Yesterday"
    ),
    BadgeItem(
      id = "b4",
      title = "Hydration Hero",
      description = "Logged 8+ glasses of water for 3 consecutive days",
      isUnlocked = false
    ),
    BadgeItem(
      id = "b5",
      title = "Ergonomics Master",
      description = "Completed all posture fixes for 14 days",
      isUnlocked = false
    )
  )
}
