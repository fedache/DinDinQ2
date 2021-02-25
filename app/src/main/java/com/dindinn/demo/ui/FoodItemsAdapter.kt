package com.dindinn.demo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dindinn.demo.R
import com.dindinn.demo.data.FoodItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_food_item.view.*
import java.util.concurrent.TimeUnit

class FoodItemsAdapter(private val fragment: CategoryFoodFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<FoodItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return object :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.single_food_item, parent, false)) {}
    }

    fun setItems(items: List<FoodItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val foodItem = items[position]
        view.titleTextView.text = foodItem.name
        view.descTextView.text = foodItem.description
        val priceCurrency = "\$ ${foodItem.price}"
        view.addCartButton.text = priceCurrency
        view.sizeTextView.text = foodItem.size
        with(view.addCartButton) {
            setOnClickListener {
                isEnabled = false
                text = context.getString(R.string.added_1)
                setBackgroundResource(R.drawable.selected_buy_button)
                postDelayed({
                    isEnabled = true
                    text = priceCurrency
                    setBackgroundResource(R.drawable.buy_button)
                }, TimeUnit.MILLISECONDS.toMillis(200))
            }
        }

        Picasso.get()
            .load(foodItem.url)
            .into(view.imageView)
    }

}