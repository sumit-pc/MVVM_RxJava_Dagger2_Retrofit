package com.example.countrylistmvvm.di

import com.example.countrylistmvvm.model.CountriesService
import com.example.countrylistmvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}