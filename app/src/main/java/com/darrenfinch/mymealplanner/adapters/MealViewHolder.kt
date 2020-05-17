package com.darrenfinch.mymealplanner.adapters

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darrenfinch.mymealplanner.model.room.Meal
import com.darrenfinch.mymealplanner.databinding.MealItemBinding

class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    private var binding = MealItemBinding.bind(itemView)
    var expanded = ObservableBoolean()
    fun bind(meal: Meal)
    {
        binding.meal = meal
        binding.viewHolder = this
        initAdapter(meal)
    }
    fun inverseExpanded()
    {
        expanded.set(!expanded.get())
    }
    private fun initAdapter(meal: Meal)
    {
        val adapter = MealFoodsRecyclerViewAdapter(meal.foods)
        binding.foodsRecyclerView.adapter = adapter
        binding.foodsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        adapter.notifyDataSetChanged()
    }
}