package com.packages.kakaomvvmpratice.model.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val API_KEY = "ed25cdd7c21ad5233472760ad0506c88"
const val BASE_URL = "https://dapi.kakao.com"

interface KakaoService {


    @GET("/v3/search/book")
    fun getImage(@Header("Authorization") auth : String,
                 @Query("query") query : String,
                 @Query("sort") sort : String?,
                 @Query("page") page : Int?,
                 @Query("size") size : Int?,
                 @Query("target") target : String?) : Single<SearchResponse>
}