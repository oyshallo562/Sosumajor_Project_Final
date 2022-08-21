package com.sosu.project

import com.google.gson.annotations.SerializedName

data class LunchModel(
    @SerializedName("MMEAL_SC_CODE") val mealCode: String, // 1: 조식, 2: 중식, 3: 석식
    @SerializedName("MLSV_YMD") var mealDate: String,
    @SerializedName("DDISH_NM") var menu: String
)
