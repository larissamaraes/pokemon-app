package com.example.pokemonapp.remote

import android.database.Observable
import com.example.pokemonapp.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Observable<ApiResponse>
}