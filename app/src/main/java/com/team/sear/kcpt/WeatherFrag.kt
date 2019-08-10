package com.team.sear.kcpt

import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.team.sear.kcpt.databinding.FragmentWeatherBinding
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.URL

class WeatherFrag : Fragment() {
    private lateinit var v: View
    private var result: String? = null
    var weatherStr: String? = null
    var binding : FragmentWeatherBinding? = null

    private lateinit var mt: WeatherTask

    private lateinit var weatherTv: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
/*
        v = inflater.inflate(R.layout.fragment_weather, container, false)
*/

        binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_weather,container,false)

        binding!!.weatherText = "Загружаем данные о погоде…"

        mt = WeatherTask()
        mt.execute()
        return binding!!.root
    }

    val weather: String
        get() {
            parseWeather()
            return result.toString()
        }

    private fun parseWeather() {
        try {
            try {
                val page = getPage("https://yandex.ru/pogoda/tyumen")
                val tapmerature = page.select("span[class=temp__value]").first().text().toString()
                result =
                        "Погода в Тюмени: \n\n$tapmerature° \n\n"
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = "Неизвестная ошибка!"
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class WeatherTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg result: String?): String? {
            try {
                weatherStr = if (weatherStr != "null") {
                    weather
                } else {
                    "Ошибка! Проверьте подключение к интернету"
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return weatherStr
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            try {
                if (weatherStr != null || weatherStr != "null" || result != "null") {
                    binding!!.weatherText = weatherStr
                } else if (weatherStr == "null") {
                    binding!!.weatherText = "Ошибка! Проверьте подключение к интернету"
                } else {
                    binding!!.weatherText = "Ошибка! Проверьте подключение к интернету"
                }
            } catch (e: Exception) {
                binding!!.weatherText = "Ошибка! Проверьте подключение к интернету"
            }
        }
    }

    @Throws(IOException::class)
    fun getPage(url: String?): Document {
        return Jsoup.parse(URL(url), 25000)
    }
}
