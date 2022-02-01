package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context,val author: ArrayList<String>,val url:ArrayList<String>,val urltoimage:ArrayList<String>, val title:MutableList<String>, var time:ArrayList<String>):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        var news_image = itemView.findViewById<ImageView>(R.id.news_image)
        var cardview = itemView.findViewById<CardView>(R.id.cardview)
        var tvAuthor = itemView.findViewById<TextView>(R.id.tvAuthor)
        var tvtime = itemView.findViewById<TextView>(R.id.tvtime)
        var favouriteimage = itemView.findViewById<ImageView>(R.id.favouriteimage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {

        holder.tv_title.text = title[position]
        Glide.with(context).load(urltoimage[position]).placeholder(R.drawable.ic_imageload)
            .into(holder.news_image)
        holder.tvAuthor.text = "By-" + author[position]
        holder.tvtime.text = "-" + time[position]
        holder.cardview.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url[position]))
            context.startActivity(intent)
        }
        holder.favouriteimage.setOnClickListener {
                holder.favouriteimage.setImageDrawable(
                    ContextCompat.getDrawable(
                        holder.favouriteimage.context,
                        R.drawable.ic_clicked_star))
        }
    }
    override fun getItemCount(): Int {
            return title.size
        }
}