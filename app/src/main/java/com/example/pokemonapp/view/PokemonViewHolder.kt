package com.example.pokemonapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.model.Pokemon

class PokemonViewHolder private constructor(
    private val binding: ItemPokemonBinding
) : RecyclerView.ViewHolder(binding.root) {

    internal fun setItem(pokemon: Pokemon) {
        binding.textPokemon.text = pokemon.name?.toUpperCase()
    }

    companion object {
        fun inflate(parent: ViewGroup) : PokemonViewHolder {
            return PokemonViewHolder(
                ItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}