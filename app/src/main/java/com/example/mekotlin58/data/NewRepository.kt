package com.example.mekotlin58.data

import android.util.Log
import com.example.mekotlin58.data.remote.apiservises.NewApi
import com.example.mekotlin58.data.remote.models.Articles
import com.example.mekotlin58.data.remote.models.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class NewRepository @Inject constructor(
    private val newsApiService: NewApi
) {

    @Singleton
    fun getNews(
        query: String,
        onResponse: (new: List<Articles>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        Log.e("tag", "getNews:Just test ", )
        newsApiService.getNews(query = query).enqueue(object : Callback<NewsResponse> {

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful && response.body() != null)
                    response.body()!!.articles?.let { onResponse(it) }
                Log.e("tags", "api:${onResponse}")
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                onFailure(t)
                Log.e("tag", "failure:${onFailure}")
            }
        })
    }
}