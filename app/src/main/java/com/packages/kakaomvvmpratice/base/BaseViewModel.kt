package com.packages.kakaomvvmpratice.base

import androidx.lifecycle.ViewModel
import com.packages.kakaomvvmpratice.utils.SnackbarMessage
import com.packages.kakaomvvmpratice.utils.SnackbarMessageString
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(){
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()


    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}