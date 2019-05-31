package com.example.pokemonapp.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.model.Pokemon

class PokemonViewHolder private constructor(
    private val binding: ItemPokemonBinding,
    private val pokemonClickedCallback: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    internal fun setItem(pokemon: Pokemon?) {
        with(binding){
            pokemon?.name?.let { name ->
                textPokemon.text = name.toUpperCase()
                root.setOnClickListener { pokemonClickedCallback.invoke(name) }
            }
        }
    }

    companion object {
        fun inflate(parent: ViewGroup, pokemonClickedCallback: (String) -> Unit) : PokemonViewHolder {
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