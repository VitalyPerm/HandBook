package ru.elvitalya.droiderhandbook.features.pokemons.ui

import ru.elvitalya.droiderhandbook.core.utils.createFakeChildStackStateFlow
import ru.elvitalya.droiderhandbook.features.pokemons.ui.list.FakePokemonListComponent

class FakePokemonsComponent : PokemonsComponent {

    override val childStack = createFakeChildStackStateFlow(
        PokemonsComponent.Child.List(FakePokemonListComponent())
    )
}
