package com.example.pokemonapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.model.Pokemon

class PokemonViewHolder private constructor(
    private val binding: ItemPokemonBinding,
    private val pokemonClickedCallback: (String?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    internal fun setItem(pokemon: Pokemon?) {
        binding.textPokemon.text = pokemon?.name?.toUpperCase()
        binding.root.setOnClickListener { pokemonClickedCallback.invoke(pokemon?.url) }
    }

    companion object {
        fun inflate(parent: ViewGroup, pokemonClickedCallback: (String?) -> Unit) : PokemonViewHolder {
            return PokemonViewHolder(
                ItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), pokemonClickedCallback
            )
        }
    }
}