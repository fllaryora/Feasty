package com.darrenfinch.mymealplanner.domain.allmeals.controller

import androidx.lifecycle.*
import com.darrenfinch.mymealplanner.InstantExecutorExtension
import com.darrenfinch.mymealplanner.TestData
import com.darrenfinch.mymealplanner.common.ScreensNavigator
import com.darrenfinch.mymealplanner.domain.allmeals.view.AllMealsViewMvc
import com.darrenfinch.mymealplanner.domain.usecases.GetAllMealsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class AllMealsControllerTest {

    private val defaultMealListLiveData = TestData.defaultMealListLiveData
    private val defaultMealListData = TestData.defaultMealListData

    private val viewLifecycleOwner = mockk<LifecycleOwner>()
    private val lifecycle = LifecycleRegistry(viewLifecycleOwner)

    private val screensNavigator = mockk<ScreensNavigator>(relaxUnitFun = true)
    private val getAllMealsUseCase = mockk<GetAllMealsUseCase>(relaxUnitFun = true)

    private val viewMvc = mockk<AllMealsViewMvc>(relaxUnitFun = true)

    private lateinit var SUT: AllMealsController

    @BeforeEach
    internal fun setUp() {
        SUT = AllMealsController(screensNavigator, getAllMealsUseCase)
        SUT.bindView(viewMvc)

        setupInstantLifecycleEventComponents()
    }

    @Test
    internal fun `onStart() subscribes to viewMvc`() {
        SUT.onStart()
        verify { viewMvc.registerListener(SUT) }
    }

    @Test
    internal fun `onStop() un-subscribes to viewMvc`() {
        SUT.onStop()
        verify { viewMvc.unregisterListener(SUT) }
    }

    @Test
    internal fun `fetchFoods() binds foods to viewMvc from use case`() {
        every { getAllMealsUseCase.fetchAllMeals() } returns defaultMealListLiveData
        SUT.fetchMeals(viewLifecycleOwner)
        verify { viewMvc.bindMeals(defaultMealListData) }
        verify { getAllMealsUseCase.fetchAllMeals() }
    }

    private fun setupInstantLifecycleEventComponents() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        every { viewLifecycleOwner.lifecycle } returns lifecycle
    }
}