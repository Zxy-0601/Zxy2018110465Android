package com.example.zxy2018110465.ui.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zxy2018110465.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.concurrent.thread

class CityViewModel (application: Application): AndroidViewModel(application){
    private val _cities:MutableLiveData<List<CityItem>> = MutableLiveData()

    val cities:LiveData<List<CityItem>> = _cities

    init {
        thread {
            val str = readFileFromRaw(R.raw.citycode)//读取资源文件,返回一个字符串
            //字符串转成一个城市列表
            val gson = Gson()
            val CityType = object : TypeToken<List<CityItem>>() {}.type
            var cts: List<CityItem> = gson.fromJson(str, CityType)
            //对资源进行过滤
            cts = cts.filter { it.city_code != "" }
            _cities.postValue(cts)

        }

    }
    //读取资源文件
    fun readFileFromRaw(rawName: Int): String? {
        try {
            val inputReader = InputStreamReader(getApplication<Application>().resources.openRawResource(rawName))
            val bufReader = BufferedReader(inputReader)
            var line: String? = ""
            var result: String? = ""
            while (bufReader.readLine().also({ line = it }) != null) {
                result += line
            }
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}