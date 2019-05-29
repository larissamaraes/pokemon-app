package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("count") val count: Long?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Pokemon>?
)