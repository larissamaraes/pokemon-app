package com.example.pokemonapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.remote.NetworkUtils
import com.example.pokemonapp.remote.paging.PokemonDataSourceFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    private val compositeDisposable = CompositeDisposable()
    private val sourceFactory: PokemonDataSourceFactory

    private var pokemonList: Observable<PagedList<Pokemon>>

    init {
        sourceFactory = PokemonDataSourceFactory(compositeDisposable, NetworkUtils.getRetrofitInstance())

        val config = PagedList.Config.Builder()
            .setPageSize(NetworkUtils.PAGE_SIZE)
            .setInitialLoadSizeHint(NetworkUtils.PAGE_SIZE)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .build()

        pokemonList = RxPagedListBuilder(sourceFactory, config)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupAdapter()
        subscribeToList()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun setupAdapter() {
        pokemonAdapter = PokemonAdapter({ })
        with(binding.recyclerViewPokemons) {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun subscribeToList() {
        compositeDisposable.add(pokemonList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    pokemonAdapter.submitList(list)
                },
                { /* Nothing to do here */ }
            )
        )
    }
}
