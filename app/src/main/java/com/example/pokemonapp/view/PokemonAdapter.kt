package com.example.pokemonapp.view

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonapp.model.Pokemon

class PokemonAdapter : PagedListAdapter<Pokemon, PokemonViewHolder>(characterDiff) {

    var pokemons: List<Pokemon> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.setItem(pokemons[position])
    }

    internal fun observePokemons(pokemons: List<Pokemon>) {
        this.pokemons = pokemons
        notifyDataSetChanged()
    }

    companion object {
        val characterDiff = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(old: Pokemon, new: Pokemon): Boolean {
                return old.url == new.url

            }

            override fun areContentsTheSame(old: Pokemon, new: Pokemon): Boolean {
                return old == new
            }

        }
    }
}