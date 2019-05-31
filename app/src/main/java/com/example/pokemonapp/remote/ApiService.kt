package com.example.pokemonapp.remote

import com.example.pokemonapp.model.ApiResponse
import com.example.pokemonapp.model.PokemonDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Observable<ApiResponse>

    @GET("pokemon/{name}/")
    fun getPokemonDetail(@Path("name") name: String): Observable<PokemonDetail>
}