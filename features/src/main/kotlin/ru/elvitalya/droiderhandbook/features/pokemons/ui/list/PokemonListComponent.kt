package ru.elvitalya.droiderhandbook.features.pokemons.ui.list

import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.pokemons.domain.Pokemon
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonId
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonType
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonTypeId

interface PokemonListComponent {

    val types: List<PokemonType>

    val selectedTypeId: StateFlow<PokemonTypeId>

    val pokemonsState: StateFlow<LoadableState<List<Pokemon>>>

    fun onTypeClick(typeId: PokemonTypeId)

    fun onPokemonClick(pokemonId: PokemonId)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class PokemonDetailsRequested(val pokemonId: PokemonId) : Output
    }
}
