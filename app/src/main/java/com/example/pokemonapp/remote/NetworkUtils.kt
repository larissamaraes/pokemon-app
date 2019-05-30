package com.example.pokemonapp.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {

    const val PAGE_LIMIT = 20

    const val PAGE_SIZE = 20

    private const val API_BASE_URL = "https://pokeapi.co/api/v2/"

    fun getRetrofitInstance(): ApiService {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create<ApiService>(ApiService::class.java)
    }

}