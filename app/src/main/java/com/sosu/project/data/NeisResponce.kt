package com.sosu.project

import com.google.gson.annotations.SerializedName

data class LunchResponse(
    @SerializedName("mealServiceDietInfo") val lunch :  List<Map<String, List<LunchModel>>>
)