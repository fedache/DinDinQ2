package com.dindinn.demo.network

import com.dindinn.demo.data.FoodItem
import com.dindinn.demo.data.Status
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AppModule {
    private val gson = Gson()

    val defaultFoodOrderWebService: FoodOrderWebService by lazy {
        object : FoodOrderWebService {
            override fun listFoodItems(itemType: String): Call<List<FoodItem>> {
                return mockCall {
                    Response.success(
                        gson.fromJson<List<FoodItem>>(
                            FOOD_ITEMS,
                            object : TypeToken<List<FoodItem>>() {}.type
                        )
                    )
                }
            }

            override fun addFoodItem(foodItem: FoodItem): Call<Status> {
                return mockCall {
                    Response.success(
                        gson.fromJson<Status>(
                            SUCCESS_STATUS,
                            object : TypeToken<Status>() {}.type
                        )
                    )
                }
            }
        }
    }
}

private fun <T> mockCall(response: () -> Response<T>): Call<T> {
    return object : Call<T> {
        override fun enqueue(callback: Callback<T>) {}

        override fun isExecuted(): Boolean = false

        override fun timeout(): Timeout = Timeout()

        override fun clone(): Call<T> = this

        override fun isCanceled(): Boolean = false

        override fun cancel() {}

        override fun execute(): Response<T> {
            return response()
        }

        override fun request(): Request {
            return Request.Builder().build()
        }
    }
}


private const val FOOD_ITEMS = """
[{
	"id": 1,
	"name": "Half & Half pizza",
	"description": "Two pizzas in one",
	"size": "Medium",
    "price": "3000",
    "url": "https://images.phi.content-cdn.io/cdn-cgi/image/height=500,quality=50/https://imagecdn.pizzahut.me/azure/yum-resources/5b39e403-392d-428e-b52d-2bbcba3cdd93/Images/ProductImages/Large/pwa-TripleMeat-030818.jpg"
},
{
    "id": 2,
	"name": "Dodo Supreme",
	"description": "Chicken, meatballs, mozzarella, red onions, green peppers, tomatoes, peri peri, pizza sauce , sweet chili sauce, fried plantain",
	"size": "Medium",
    "price": "3400",
    "url": "https://images.phi.content-cdn.io/cdn-cgi/image/height=500,quality=50/https://imagecdn.pizzahut.me/azure/yum-resources/5b39e403-392d-428e-b52d-2bbcba3cdd93/Images/ProductImages/Large/pwa-BBQChicken-n-Macon-030818.jpg"
},
{
    "id": 3,
	"name": "Chicken Curry",
	"description": "Red onions, bell peppers, chicken, pineapple, mozzarella, tomato sauce, curry, chili peppers",
	"size": "Medium",
    "price": "3100",
    "url": "https://images.phi.content-cdn.io/cdn-cgi/image/height=500,quality=50/https://imagecdn.pizzahut.me/azure/yum-resources/5b39e403-392d-428e-b52d-2bbcba3cdd93/Images/ProductImages/Large/pwa-BBQMeatLovers-030818.jpg"
},
{
    "id": 4,
	"name": "Chicken BBQ",
	"description": "Chicken, red onions, corn, mozzarella, bbq sauce, tomato sauce",
	"size": "Medium",
    "price": "3100",
    "url":"https://images.phi.content-cdn.io/cdn-cgi/image/height=500,quality=50/https://imagecdn.pizzahut.me/azure/yum-resources/5b39e403-392d-428e-b52d-2bbcba3cdd93/Images/ProductImages/Large/pwa-TikkaChicken-030818.jpg"
}
]
"""

const val SUCCESS_STATUS = """
    {"created": true}
"""