package com.dindinn.demo.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dindinn.demo.R

class SlidePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val imageResId = listOf(
        R.drawable.pizza_black,
        R.drawable.pizza_black,
        R.drawable.pizza_black
    )

    override fun getItemCount(): Int {
        return imageResId.size
    }

    override fun createFragment(position: Int): Fragment {
        return IntroPageFragment.newInstance(imageResId[position])
    }
}