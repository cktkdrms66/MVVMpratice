package com.packages

import android.app.Application
import com.packages.kakaomvvmpratice.adapter.MainAdapter
import com.packages.kakaomvvmpratice.model.DataModel
import com.packages.kakaomvvmpratice.model.DataModelImpl
import com.packages.kakaomvvmpratice.model.service.BASE_URL
import com.packages.kakaomvvmpratice.model.service.KakaoService
import com.packages.kakaomvvmpratice.viewmodel.MainViewModel
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(){

    var retrofitPart = module {
        single<KakaoService>{
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoService::class.java)
        }
    }

    var modelPart = module{
        factory<DataModel> {
            DataModelImpl(get())
        }
    }

    var viewModelPart = module {
        viewModel {
            MainViewModel(get())
        }
    }

    var adapterPart = module {
        factory<MainAdapter>{
            MainAdapter()
        }
    }


    var myDiModule = listOf(retrofitPart, modelPart, viewModelPart, adapterPart)

    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)

    }
}