package com.packages.kakaomvvmpratice.model

import com.packages.kakaomvvmpratice.model.service.API_KEY
import com.packages.kakaomvvmpratice.model.service.KakaoService
import com.packages.kakaomvvmpratice.model.service.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DataModel{
    fun getData(query : String, page : Int?) : Single<SearchResponse>
}

class DataModelImpl(val service : KakaoService) : DataModel{
    override fun getData(query : String, page : Int?): Single<SearchResponse> {
        return service.getImage("KakaoAK $API_KEY", query, "accuracy", page, 20, null)
    }
}