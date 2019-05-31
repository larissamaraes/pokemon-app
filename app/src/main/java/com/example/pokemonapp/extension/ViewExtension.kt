package com.example.pokemonapp.extension

import android.view.View

fun View.isLoading(isLoading: Boolean) {
    this.visibility = if (isLoading) View.VISIBLE else View.GONE
}