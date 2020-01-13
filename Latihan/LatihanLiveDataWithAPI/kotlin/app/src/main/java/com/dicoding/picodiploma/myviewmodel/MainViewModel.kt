package com.dicoding.picodiploma.myviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat
import java.util.*

/**
 * Created by Gilang on 04/31/2019.
 */

class MainViewModel : ViewModel() {

    companion object {
          private const val API_KEY = "ISI SESUAI API_KEY ANDA"
//          private const val API_KEY = "cb744b309dbc7c577fe57bde64e8cf3a"
    }
    private val listWeathers = MutableLiveData<ArrayList<WeatherItems>>()

    internal fun setWeather(cities: String) {
        val client = AsyncHttpClient()
        val listItems = ArrayList<WeatherItems>()
        val url = "https://api.openweathermap.org/data/2.5/group?id=$cities&units=metric&appid=$API_KEY"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("list")

                    for (i in 0 until list.length()) {
                        val weather = list.getJSONObject(i)
                        val weatherItems = WeatherItems()
                        weatherItems.id = weather.getInt("id")
                        weatherItems.name = weather.getString("name")
                        weatherItems.currentWeather = weather.getJSONArray("weather").getJSONObject(0).getString("main")
                        weatherItems.description = weather.getJSONArray("weather").getJSONObject(0).getString("description")
                        val tempInKelvin = weather.getJSONObject("main").getDouble("temp")
                        val tempInCelsius = tempInKelvin - 273
                        weatherItems.temperature = DecimalFormat("##.##").format(tempInCelsius)
                        listItems.add(weatherItems)
                    }
                    listWeathers.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    internal fun getweathers(): LiveData<ArrayList<WeatherItems>> {
        return listWeathers
    }
}
