package com.sosu.project

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LunchService {
    @GET("/hub/mealServiceDietInfo/")
    suspend fun getLunch(
        @Query("Key") apiKey: String,
        @Query("Type") type: String,
        @Query("pIndex") index :Int,
        @Query("pSize") size :Int,
        @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("MLSV_FROM_YMD") startDate: String,
        @Query("MLSV_TO_YMD") endDate: String
    ): Response<LunchResponse?>
}