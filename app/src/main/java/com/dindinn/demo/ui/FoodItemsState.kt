package com.dindinn.demo.ui

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dindinn.demo.data.FoodItem

data class FoodItemsState(val list: Async<List<FoodItem>> = Uninitialized): MavericksState