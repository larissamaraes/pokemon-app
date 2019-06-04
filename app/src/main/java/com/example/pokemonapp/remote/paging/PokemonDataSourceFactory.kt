package com.example.pokemonapp.remote.paging

import androidx.paging.DataSource
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.remote.ApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class PokemonDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val apiService: ApiService,
    private val hasMorePagesCallback: (Boolean) -> Unit
) : DataSource.Factory<Int, Pokemon>() {

    override fun create(): DataSource<Int, Pokemon> {
        return PokemonDataSource(apiService, compositeDisposable, hasMorePagesCallback)
    }
}