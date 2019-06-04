package com.example.pokemonapp.view.main

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.model.Pokemon

class PokemonAdapter(
    private val pokemonClickedCallback: (String) -> Unit
) : PagedListAdapter<Pokemon, RecyclerView.ViewHolder>(characterDiff) {

    private var hasMorePages: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == POKEMON_VIEW_TYPE) {
            PokemonViewHolder.inflate(parent, pokemonClickedCallback)
        }
        else {
            ProgressViewHolder.inflate(parent)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasMorePages) 1 else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == POKEMON_VIEW_TYPE) (holder as? PokemonViewHolder)?.setItem(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasMorePages && position == itemCount - 1) {
            PROGRESS_VIEW_TYPE
        } else {
            POKEMON_VIEW_TYPE
        }
    }

    internal fun updateHasMorePages(hasMorePages: Boolean) {
        this.hasMorePages = hasMorePages
        if (!hasMorePages) notifyDataSetChanged()
    }

    companion object {
        private const val PROGRESS_VIEW_TYPE = 0
        private const val POKEMON_VIEW_TYPE = 1

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