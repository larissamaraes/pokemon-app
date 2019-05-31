package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail (
    @SerializedName("name") val name: String?,
    @SerializedName("base_experience") val baseExperience: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("weight") val weight: Int?
)