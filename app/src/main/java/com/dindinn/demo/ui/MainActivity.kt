package com.dindinn.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dindinn.demo.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slidePagerAdapter = SlidePagerAdapter(this)
        slideViewPager.adapter = slidePagerAdapter
        TabLayoutMediator(tabLayout, slideViewPager) { _, _ -> }.attach()

        val foodItemsAdapter = FoodCategoryPagerAdapter(this)
        snackItemsViewPager.adapter = foodItemsAdapter
        TabLayoutMediator(categoryTabLayout, snackItemsViewPager) { tab, position ->
            tab.text = foodItemsAdapter.categories[position].name
        }.attach()

        cartFab.setOnClickListener {
            //TODO: CartActivity.startActivity(this)
        }
    }
}