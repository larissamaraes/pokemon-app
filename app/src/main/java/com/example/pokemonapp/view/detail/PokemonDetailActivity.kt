package com.example.pokemonapp.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityPokemonDetailBinding
import com.example.pokemonapp.extension.isLoading
import com.example.pokemonapp.model.PokemonDetail
import com.example.pokemonapp.remote.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailBinding

    private val pokemonNameExtra: String by lazy { intent.getStringExtra(POKEMON_NAME) }
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getPokemonDetail()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun getPokemonDetail() {
        binding.placeholderLoading.loading.isLoading(true)
        compositeDisposable.add(
            NetworkUtils.getRetrofitInstance().getPokemonDetail(pokemonNameExtra)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onSuccess, ::onFailure)
        )
    }

    private fun onSuccess(detail: PokemonDetail) {
        binding.placeholderLoading.loading.isLoading(false)
        with(binding) {
            textPokemonName.text = detail.name?.toUpperCase()
            textBaseExperience.text = getString(R.string.experience_value, detail.baseExperience.toString())
            textHeight.text = getString(R.string.height_value, detail.height.toString())
            textWeight.text = getString(R.string.weight_value, detail.weight.toString())
        }
    }

    private fun onFailure(throwable: Throwable) {
        Toast.makeText(this, "Something went wrong :(", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val POKEMON_NAME = "POKEMON_NAME"
        fun startActivity(context: Context, pokemonName: String) {
            context.startActivity(
                Intent(context, PokemonDetailActivity::class.java)
                    .apply { putExtra(POKEMON_NAME, pokemonName) }
            )
        }
    }
}