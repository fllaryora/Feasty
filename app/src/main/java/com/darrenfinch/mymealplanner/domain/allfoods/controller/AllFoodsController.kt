package com.darrenfinch.mymealplanner.domain.allfoods.controller

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.darrenfinch.mymealplanner.common.Constants
import com.darrenfinch.mymealplanner.domain.allfoods.view.AllFoodsViewMvc
import com.darrenfinch.mymealplanner.model.FoodsRepository

class AllFoodsController(private val repository: FoodsRepository) : AllFoodsViewMvc.Listener {

    private lateinit var viewMvc: AllFoodsViewMvc

    private fun navigateToAddEditFoodFragment(foodId: Int) {
        val directions =
            AllFoodsFragmentDirections.actionFoodsFragmentToAddEditFoodFragment(
                foodId
            )
        findNavController(viewMvc.getRootView()).navigate(directions)
    }

    fun bindView(viewMvc: AllFoodsViewMvc) {
        this.viewMvc = viewMvc
    }

    fun onStart() {
        viewMvc.registerListener(this)
    }

    fun onStop() {
        viewMvc.unregisterListener(this)
    }

    fun fetchFoods(viewLifecycleOwner: LifecycleOwner) {
        repository.getFoods().observe(viewLifecycleOwner, Observer { newFoods ->
            viewMvc.bindFoods(newFoods)
        })
    }

    override fun addNewFoodClicked() {
        navigateToAddEditFoodFragment(Constants.DEFAULT_FOOD_ID)
    }

    override fun onItemEdit(foodId: Int) {
        navigateToAddEditFoodFragment(foodId)
    }

    override fun onItemDelete(foodId: Int) {
        repository.deleteFood(foodId)
    }
}