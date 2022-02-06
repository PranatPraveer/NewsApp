package com.example.newsapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    var Article=MutableLiveData<List<Article>>()
    fun getNews(){
        val NewsAPI= RetrofitHelper.getInstance().create(NewsAPI::class.java)
        GlobalScope.launch {
            val result=NewsAPI.getNews("08dd0f1e64f24ddbae9364b6e172b532")
            Article.postValue(result.body()?.articles)
        }
    }
}