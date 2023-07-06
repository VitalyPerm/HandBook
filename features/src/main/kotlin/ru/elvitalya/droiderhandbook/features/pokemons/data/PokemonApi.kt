package ru.elvitalya.droiderhandbook.features.pokemons.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import ru.elvitalya.droiderhandbook.features.pokemons.data.dto.DetailedPokemonResponse
import ru.elvitalya.droiderhandbook.features.pokemons.data.dto.PokemonsByTypeResponse

interface PokemonApi {

    @GET("/api/v2/type/{typeId}")
    suspend fun getPokemonsByType(@Path("typeId") typeId: String): PokemonsByTypeResponse

    @GET("/api/v2/pokemon/{pokemonId}")
    suspend fun getPokemonById(@Path("pokemonId") pokemonId: String): DetailedPokemonResponse
}
