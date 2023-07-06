package ru.elvitalya.droiderhandbook.features.pokemons.domain

data class DetailedPokemon(
    val id: PokemonId,
    val name: String,
    val height: Float,
    val weight: Float,
    val imageUrl: String,
    val types: List<PokemonType>
)