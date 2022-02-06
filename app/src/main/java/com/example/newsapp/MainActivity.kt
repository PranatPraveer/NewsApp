package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var search: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        search=findViewById(R.id.searchView)
        val MainViewModel:MainViewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        MainViewModel.getNews()
        MainViewModel.Article.observe(this, Observer {
            Log.d("Articles", MainViewModel.Article.value?.get(3)?.title.toString())
            val NewsAdapter = NewsAdapter(this,MainViewModel.Article)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = NewsAdapter
            recyclerView.setHasFixedSize(true)
        })

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                //MainViewModel.Article.value?.filter { it.title == newText }
                MainViewModel.Article.value?.forEach {
                    if(it.title==newText){
                        MainViewModel.Article.value
                    }

                }
                recyclerView.adapter?.notifyDataSetChanged()
                   // MainViewModel.Article.value=MainViewModel.Article.value?.filter {
                     //   it.title == newText
                    //}
                return true
            }
    })

}
}