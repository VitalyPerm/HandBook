package ru.elvitalya.droiderhandbook.features.pokemons.ui.details

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.single.Replica
import ru.elvitalya.droiderhandbook.core.error_handling.ErrorHandler
import ru.elvitalya.droiderhandbook.core.utils.observe
import ru.elvitalya.droiderhandbook.features.pokemons.domain.DetailedPokemon

class RealPokemonDetailsComponent(
    componentContext: ComponentContext,
    private val pokemonReplica: Replica<DetailedPokemon>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, PokemonDetailsComponent {

    override val pokemonState = pokemonReplica.observe(lifecycle, errorHandler)

    override fun onRetryClick() {
        pokemonReplica.refresh()
    }

    override fun onRefresh() {
        pokemonReplica.refresh()
    }
}
