package ru.elvitalya.droiderhandbook.features.pokemons.ui.list

import kotlinx.coroutines.flow.MutableStateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.pokemons.domain.Pokemon
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonId
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonType
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonTypeId

class FakePokemonListComponent : PokemonListComponent {

    override val types = listOf(
        PokemonType.Fire,
        PokemonType.Water,
        PokemonType.Electric,
        PokemonType.Grass,
        PokemonType.Poison
    )

    override val selectedTypeId = MutableStateFlow(types[0].id)

    override val pokemonsState = MutableStateFlow(
        LoadableState(
            loading = true,
            data = listOf(
                Pokemon(
                    id = PokemonId("1"),
                    name = "Bulbasaur"
                ),
                Pokemon(
                    id = PokemonId("5"),
                    name = "Charmeleon"
                ),
                Pokemon(
                    id = PokemonId("7"),
                    name = "Squirtle"
                )
            )
        )
    )

    override fun onTypeClick(typeId: PokemonTypeId) = Unit

    override fun onPokemonClick(pokemonId: PokemonId) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}