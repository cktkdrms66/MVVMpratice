package com.packages.kakaomvvmpratice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.packages.kakaomvvmpratice.base.BaseViewModel
import com.packages.kakaomvvmpratice.model.DataModel
import com.packages.kakaomvvmpratice.model.service.Document
import com.packages.kakaomvvmpratice.model.service.SearchResponse
import com.packages.kakaomvvmpratice.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val model: DataModel) : BaseViewModel(){

    private val _searchResponseLiveData = MutableLiveData<List<Document>>()
    val searchResponseLiveData : LiveData<List<Document>>
        get() = _searchResponseLiveData

    private val _snackbarMessage = SingleLiveEvent<Int>()
    val snackbarMessage : LiveData<Int>
        get() = _snackbarMessage


    fun getImageSearch(query : String, page : Int?){
        addDisposable(model.getData(query, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run{
                    if(documents.size > 0){
                        val list = it.documents.map{
                            Document(it.thumbnail, it.title)
                        }
                        var oldList = _searchResponseLiveData.value!! as ArrayList
                        oldList.addAll(list)
                        _searchResponseLiveData.postValue(oldList)
                    }
                }
            },{it.printStackTrace()}))
    }

    fun getImageSearchAfterRemove(query : String, page : Int?){
        addDisposable(model.getData(query, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run{
                    if(documents.size > 0){
                        val list = it.documents.map{
                            Document(it.thumbnail, it.title)
                        }
                        _searchResponseLiveData.postValue(list)
                    }
                }
            },{it.printStackTrace()}))
    }

    fun doSomething(){
        println("dosomething")
        _snackbarMessage.call()
    }
}