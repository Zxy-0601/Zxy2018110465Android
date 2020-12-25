package com.example.zxy2018110465.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zxy2018110465.R
import kotlinx.android.synthetic.main.fragment_city.*

class CityFragment : AppCompatActivity() {

    lateinit var viewModel:CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_city)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(CityViewModel::class.java)
        viewModel.cities.observe(this, Observer {
            val cities = it
            val adapter = ArrayAdapter<CityItem>(this, android.R.layout.simple_list_item_1, cities)//准备适配器
            listView.adapter = adapter
            listView.setOnItemClickListener { _, _, position, _ ->
                val CityCode = cities[position].city_code
                //构建意图
                val intent = Intent(this, Main2Activity::class.java)
                intent.putExtra("city_code", CityCode)
                startActivity(intent)
            }
        })
    }
}