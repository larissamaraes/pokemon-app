package com.example.pokemonapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonBinding

class PokemonViewHolder private constructor(
    private val binding: ItemPokemonBinding
) : RecyclerView.ViewHolder(binding.root) {

    internal fun setItem(pokemon: String) {
        binding.textPokemon.text = pokemon
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