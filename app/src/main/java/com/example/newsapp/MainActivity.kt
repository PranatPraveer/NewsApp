package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        val MainViewModel:MainViewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        MainViewModel.getNews()
        MainViewModel.mdata.observe(this, Observer {
            Log.d("a",it.title)
            val NewsAdapter = NewsAdapter(this,MainViewModel.author,MainViewModel.url,MainViewModel.urltoimage,MainViewModel.title,MainViewModel.time)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = NewsAdapter
            recyclerView.setHasFixedSize(true)
        })
    }

}