package com.example.pokemonapp.view

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonapp.model.Pokemon

class PokemonAdapter : PagedListAdapter<Pokemon, PokemonViewHolder>(characterDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.setItem(getItem(position))
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