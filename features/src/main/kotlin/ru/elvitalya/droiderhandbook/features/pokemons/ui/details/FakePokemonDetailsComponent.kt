package ru.elvitalya.droiderhandbook.features.pokemons.ui.details

import kotlinx.coroutines.flow.MutableStateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.pokemons.domain.DetailedPokemon
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonId
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonType

class FakePokemonDetailsComponent : PokemonDetailsComponent {

    override val pokemonState = MutableStateFlow(
        LoadableState(
            loading = true,
            data = DetailedPokemon(
                id = PokemonId("1"),
                name = "Bulbasaur",
                imageUrl = "",
                height = 0.7f,
                weight = 6.9f,
                types = listOf(PokemonType.Grass, PokemonType.Poison)
            )
        )
    )

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}
