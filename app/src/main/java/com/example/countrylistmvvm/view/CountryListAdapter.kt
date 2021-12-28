package com.example.countrylistmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrylistmvvm.model.Country
import com.example.countrylistmvvm.R
import com.example.countrylistmvvm.util.getProgressDrawable
import com.example.countrylistmvvm.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(private var countries : ArrayList<Country>):
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View):RecyclerView.ViewHolder(view){

        private val imageView = view.imageView
        private val countryName = view.name
        private val countryCapital = view.capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country){
            countryCapital.text = country.capital
            countryName.text = country.countryName
            imageView.loadImage(country.flag, progressDrawable)

        }
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
//        return CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false))
//
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
//        return CountryViewHolder(view)
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false))




    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size

    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }
}