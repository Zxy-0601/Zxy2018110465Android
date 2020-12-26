package com.example.zxy2018110465.ui.city

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zxy2018110465.R
import kotlinx.android.synthetic.main.fragment_city.*

class CityFragment : Fragment() {
    companion object {
        fun newInstance() = CityFragment()
    }

    private lateinit var viewModel: CityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(activity?.application!!)).get(CityViewModel::class.java)
        viewModel.cities.observe(this, Observer {
            val cities = it
            val adapter = ArrayAdapter<CityItem>(Activity(), android.R.layout.simple_list_item_1, cities)//准备适配器
            listView.adapter = adapter
            listView.setOnItemClickListener { _, _, position, _ ->
                val CityCode = cities[position].city_code
                //构建意图
                val intent = Intent(this.context, Main2Activity::class.java)
                intent.putExtra("city_code", CityCode)
                startActivity(intent)
            }
        })
    }
}