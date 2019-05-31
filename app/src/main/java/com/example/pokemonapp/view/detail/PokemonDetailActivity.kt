package com.example.pokemonapp.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityPokemonDetailBinding

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailBinding

    private val pokemonNameExtra : String by lazy { intent.getStringExtra(POKEMON_NAME) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_detail)
        getPokemonDetail()
    }

    private fun getPokemonDetail() {

    }

    companion object {
        private const val POKEMON_NAME = "POKEMON_NAME"
        fun startActivity(context: Context, pokemonName: String) {
            context.startActivity(
                Intent(context, PokemonDetailActivity::class.java)
                    .apply { putExtra(POKEMON_NAME, pokemonName) }
            )
        }
    }
}