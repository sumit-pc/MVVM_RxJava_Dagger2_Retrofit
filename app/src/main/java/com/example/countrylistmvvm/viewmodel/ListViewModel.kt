package com.example.countrylistmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrylistmvvm.di.DaggerApiComponent
import com.example.countrylistmvvm.model.Country
import com.example.countrylistmvvm.model.CountriesService
import com.example.countrylistmvvm.model.CountriesService_MembersInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var countriesService : CountriesService

    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    init {
        DaggerApiComponent.create().inject(this)
    }

//    private fun fetchCountries(){
//        val mockData = listOf(Country("CountryA"),
//            Country("CountryB"),
//            Country("CountryC"),
//            Country("CountryD"),
//            Country("CountryE"),
//            Country("CountryF"),
//            Country("CountryG"),
//            Country("CountryH"),
//            Country("CountryI"),
//            Country("CountryJ"),
//            Country("CountryK")
//        )
//
//        countryLoadError.value = false
//        loading.value = false
//        countries.value = mockData
//    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        countries.value = t
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}