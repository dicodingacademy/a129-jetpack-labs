package com.dicoding.picodiploma.myviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_items.view.*
import java.util.*

/**
 * Created by Emeth on 10/31/2016.
 */

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val mData = ArrayList<WeatherItems>()

    /**
     * Gunakan method ini jika semua datanya akan diganti
     *
     * @param items kumpulan data baru yang akan mengganti semua data yang sudah ada
     */
    fun setData(items: ArrayList<WeatherItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * Gunakan method ini jika ada 1 data yang ditambahkan
     *
     * @param item data baru yang akan ditambahkan
     */
    fun addItem(item: WeatherItems) {
        mData.add(item)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): WeatherViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.weather_items, viewGroup, false)
        return WeatherViewHolder(mView)
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherItems: WeatherItems) {
            with(itemView){
                textKota.text = weatherItems.name
                textTemp.text = weatherItems.temperature
                textDesc.text = weatherItems.description
            }
        }
    }
}





