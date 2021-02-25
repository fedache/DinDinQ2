package com.dindinn.demo.network

import com.dindinn.demo.data.FoodItem
import com.dindinn.demo.data.Status
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodOrderWebService {
    @GET("/foodItems/{itemType}")
    fun listFoodItems(@Path("itemType") itemType: String): Call<List<FoodItem>>

    @POST("/foodItems/addToCart")
    fun addFoodItem(@Body foodItem: FoodItem): Call<Status>
}