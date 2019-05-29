package com.example.pokemonapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.model.ApiResponse
import com.example.pokemonapp.remote.ApiService
import com.example.pokemonapp.remote.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupAdapter()
        getPokemons()
    }

    private fun setupAdapter() {
        pokemonAdapter = PokemonAdapter()
        with(binding.recyclerViewPokemons){
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun getPokemons() {
        val retrofitClient = NetworkUtils.getRetrofitInstance()
        val endpoint = retrofitClient.create(ApiService::class.java)
        val callback = endpoint.getPokemons(0, 20)

        callback.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No Pokémons found :(", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                with(response.body()?.results ?: listOf()) {
                    if (isEmpty()) {
                        Toast.makeText(this@MainActivity, "No Pokémons found :(", Toast.LENGTH_LONG).show()
                    } else {
                        pokemonAdapter.observePokemons(this)
                    }
                }
            }

        })
    }
}
