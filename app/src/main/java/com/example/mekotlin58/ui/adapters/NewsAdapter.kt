package com.example.mekotlin58.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mekotlin58.R
import com.example.mekotlin58.data.remote.models.Articles
import com.example.mekotlin58.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var _newsList = mutableListOf<Articles>()

    fun setNewsList(newsList: List<Articles>) {
        this._newsList.addAll(newsList)
        this._newsList.distinctBy {
            it.urlToImage
        }
        notifyDataSetChanged()
    }


    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(articles: Articles) = with(binding) {
            val imageUrl = articles.urlToImage
            Glide.with(ivCover)
                .load(imageUrl)
                .error(R.drawable.error)
                .into(ivCover)
            tvTitle.text = articles.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = _newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(_newsList[position])
    }
}