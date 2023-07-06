package ru.elvitalya.droiderhandbook.features.pokemons.ui.details

import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.pokemons.domain.DetailedPokemon

interface PokemonDetailsComponent {

    val pokemonState: StateFlow<LoadableState<DetailedPokemon>>

    fun onRetryClick()

    fun onRefresh()
}
