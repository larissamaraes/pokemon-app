package com.example.pokemonapp.remote.paging

import androidx.paging.PageKeyedDataSource
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.remote.ApiService
import com.example.pokemonapp.remote.NetworkUtils
import io.reactivex.disposables.CompositeDisposable

class PokemonDataSource(
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable,
    private val hasMorePages: (Boolean) -> Unit
) : PageKeyedDataSource<Int, Pokemon>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Pokemon>) {
        createObservable(0, 1, params.requestedLoadSize, callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {
        createObservable(params.key, params.key + 1, params.requestedLoadSize, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {
        createObservable(params.key, params.key - 1, params.requestedLoadSize, null, callback)
    }


    private fun createObservable(
        requestedPage: Int, adjacentPage: Int, requestedLoadSize: Int,
        initialCallback: LoadInitialCallback<Int, Pokemon>?, callback: LoadCallback<Int, Pokemon>?
    ) {
        compositeDisposable.add(
            apiService.getPokemons(requestedPage * requestedLoadSize, NetworkUtils.PAGE_LIMIT)
                .subscribe(
                    { response ->
                        response.results?.run {
                            initialCallback?.onResult(this, null, adjacentPage)
                            callback?.onResult(this, adjacentPage)
                            hasMorePages.invoke(this.isNotEmpty())
                        }
                    },
                    { /* Nothing to do here */ }
                )
        )
    }
}