package com.example.countrylistmvvm.di

import com.example.countrylistmvvm.model.CountriesApi
import com.example.countrylistmvvm.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val baseUrl = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesApi(): CountriesApi{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    fun povidedCountriesService(): CountriesService{
        return CountriesService()
    }
}