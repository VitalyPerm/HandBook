package ru.elvitalya.droiderhandbook.features.pokemons.data

import me.aartikov.replica.keyed.KeyedReplica
import ru.elvitalya.droiderhandbook.features.pokemons.domain.DetailedPokemon
import ru.elvitalya.droiderhandbook.features.pokemons.domain.Pokemon
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonId
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonTypeId

interface PokemonRepository {

    val pokemonsByTypeReplica: KeyedReplica<PokemonTypeId, List<Pokemon>>

    val pokemonByIdReplica: KeyedReplica<PokemonId, DetailedPokemon>
}