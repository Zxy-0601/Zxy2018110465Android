package com.example.zxy2018110465.ui.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.zxy2018110465.ui.notifications.weather.Forecast
import com.example.zxy2018110465.ui.notifications.weather.Weather
import com.example.zxy2018110465.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.listView

class Main2Activity : AppCompatActivity() {

    val baseURL="http://t.weather.itboy.net/api/weather/city/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_weather)
        //获得意图中的参数
        val CityCode=intent.getStringExtra("city_code")

        //获取网络
        val queue=Volley.newRequestQueue(this)
        val stringRequest=StringRequest(baseURL+CityCode,{
            //根据拿到的内容进行解析
            val gson=Gson()
            val WeatherType=object :TypeToken<Weather>(){}.type
            val weather=gson.fromJson<Weather>(it,WeatherType)//得到weather对象

            //在界面上填充结果
            textView_city.text=weather.cityInfo.city
            textView_province.text=weather.cityInfo.parent
            textView_shidu.text=weather.data.shidu
            textView_wendu.text=weather.data.wendu
            //天气
            val firstDay=weather.data.forecast.first()
            when(firstDay.type){
                "晴"->imageView.setImageResource(R.drawable.sun)
                "阴"->imageView.setImageResource(R.drawable.cloud)
                "多云"->imageView.setImageResource(R.drawable.mcloud)
                "阵雨"->imageView.setImageResource(R.drawable.rain)
                "雪"->imageView.setImageResource(R.drawable.snow)
                else->imageView.setImageResource(R.drawable.thunder)
            }
            val adapter= ArrayAdapter<Forecast>(this,android.R.layout.simple_list_item_1,weather.data.forecast)//准备适配器
            listView.adapter=adapter



            Log.d("Main2Activity","${weather.cityInfo.city} ${weather.cityInfo.parent}")

        },{
            Log.d("Main2Activity","$it")
        })
        queue.add(stringRequest)
    }
}
