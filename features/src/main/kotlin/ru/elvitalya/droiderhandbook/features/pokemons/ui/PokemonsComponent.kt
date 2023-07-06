package ru.elvitalya.droiderhandbook.features.pokemons.ui

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.features.pokemons.ui.details.PokemonDetailsComponent
import ru.elvitalya.droiderhandbook.features.pokemons.ui.list.PokemonListComponent

interface PokemonsComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class List(val component: PokemonListComponent) : Child
        class Details(val component: PokemonDetailsComponent) : Child
    }
}
