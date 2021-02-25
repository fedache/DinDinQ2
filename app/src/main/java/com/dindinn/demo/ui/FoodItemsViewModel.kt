package com.dindinn.demo.ui

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.ViewModelContext
import com.dindinn.demo.data.FoodCategory
import com.dindinn.demo.network.AppModule
import com.dindinn.demo.network.FoodOrderWebService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers.io

class FoodItemsViewModel(private val foodOrderWebService: FoodOrderWebService) :
    BaseMvRxViewModel<FoodItemsState>(FoodItemsState(Uninitialized)) {

    fun fetchFoodItems(item: FoodCategory) {
        val observable = Observable.fromCallable {
            val response = foodOrderWebService
                .listFoodItems(item.name)
                .execute()
            if (response.isSuccessful)
                response.body()!!
            else
               throw Throwable("Failed")
        }.subscribeOn(io())

        observable.execute {
            copy(list = it)
        }
    }

    companion object : MavericksViewModelFactory<FoodItemsViewModel, FoodItemsState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: FoodItemsState
        ): FoodItemsViewModel? {
            return FoodItemsViewModel(AppModule.defaultFoodOrderWebService)
        }
    }
}

