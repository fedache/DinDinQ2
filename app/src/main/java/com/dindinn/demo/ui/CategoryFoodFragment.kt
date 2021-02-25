package com.dindinn.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import com.dindinn.demo.R
import com.dindinn.demo.data.FoodCategory
import kotlinx.android.synthetic.main.fragment_category_food.*

class CategoryFoodFragment : Fragment(), MavericksView {
    private val viewModel: FoodItemsViewModel by fragmentViewModel()
    private val foodItemsAdapter by lazy { FoodItemsAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return layoutInflater.inflate(R.layout.fragment_category_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemsRecyclerView.adapter = foodItemsAdapter
        viewModel.fetchFoodItems(FoodCategory.PIZZA)
    }

    companion object {
        fun newInstance(type: FoodCategory): CategoryFoodFragment {
            return CategoryFoodFragment()
        }
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            when (state.list) {
                Uninitialized -> {
                }
                is Loading -> {
                    progressIndicator.visibility  = View.VISIBLE
                }
                is Success -> {
                    progressIndicator.visibility  = View.GONE
                    state.list.invoke().let { foodItemsAdapter.setItems(it) }
                }
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        R.string.unable_to_fetch_items,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}