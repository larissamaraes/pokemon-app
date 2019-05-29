package com.example.pokemonapp.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

    var pokemons: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.setItem(pokemons[position])
    }

    internal fun observePokemons(pokemons: List<String>) {
        this.pokemons = pokemons
    }
}