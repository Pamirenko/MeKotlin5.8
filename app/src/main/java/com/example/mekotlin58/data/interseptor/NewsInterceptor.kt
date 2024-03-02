package com.example.mekotlin58.data.interseptor

import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor(private val apiKey:String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpClient = originalRequest.url

        val url = originalHttpClient.newBuilder()
            .addQueryParameter("apiKey",apiKey)
            .build()
        val requestBuilder = originalRequest.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}