package com.example.newsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    var mdata= MutableLiveData<Article>()
    var title= mutableListOf<String>()
    var urltoimage= ArrayList<String>()
    var author= ArrayList<String>()
    var url= ArrayList<String>()
    var time= ArrayList<String>()
    fun getNews(){
        val NewsAPI= RetrofitHelper.getInstance().create(NewsAPI::class.java)
        GlobalScope.launch {
            val result=NewsAPI.getNews("08dd0f1e64f24ddbae9364b6e172b532")
            if ( result !=null ){
                result.body()?.articles?.forEach {
                    mdata.postValue(it)
                    title.add(it.title)
                    urltoimage.add(it.urlToImage)
                    author.add(it.author)
                    url.add(it.url)
                    time.add(it.publishedAt)
                }
            }
        }
    }
}