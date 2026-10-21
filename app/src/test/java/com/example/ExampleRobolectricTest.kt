package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.SampleData
import com.example.model.DietType
import com.example.viewmodel.Desk2FitViewModel
import com.example.viewmodel.ScreenTab
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Desk2Fit", appName)
  }

  @Test
  fun `test workout routines and diet plans loaded`() {
    assertTrue(SampleData.routines.isNotEmpty())
    assertTrue(SampleData.meals.any { it.dietType == DietType.VEGETARIAN })
    assertTrue(SampleData.meals.any { it.dietType == DietType.NON_VEGETARIAN })
    assertTrue(SampleData.meals.any { it.dietType == DietType.JAIN })
    assertTrue(SampleData.meals.any { it.dietType == DietType.EGGETARIAN })
  }

  @Test
  fun `test viewModel habit and water tracking`() {
    val vm = Desk2FitViewModel()
    val initialWater = vm.uiState.value.waterGlassesLogged
    vm.incrementWater()
    assertEquals(initialWater + 1, vm.uiState.value.waterGlassesLogged)

    vm.selectTab(ScreenTab.MEALS)
    assertEquals(ScreenTab.MEALS, vm.uiState.value.currentTab)

    val routine = SampleData.routines.first()
    vm.startWorkout(routine)
    assertNotNull(vm.uiState.value.activeWorkout)
    assertEquals(routine.title, vm.uiState.value.activeWorkout?.routine?.title)
  }
}
