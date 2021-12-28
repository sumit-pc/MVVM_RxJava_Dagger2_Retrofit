package com.example.countrylistmvvm.model

import com.example.countrylistmvvm.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {

//    private val url = "https://raw.githubusercontent.com"
//    private val api: CountriesApi = Retrofit.Builder()
//        .baseUrl(url)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(CountriesApi::class.java)

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries() : Single<List<Country>>{
        return api.getCountries()
    }
}