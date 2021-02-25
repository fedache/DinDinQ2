package com.dindinn.demo.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dindinn.demo.data.FoodCategory

class FoodCategoryPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    val categories = FoodCategory.values()
    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryFoodFragment.newInstance(categories[position])
    }
}