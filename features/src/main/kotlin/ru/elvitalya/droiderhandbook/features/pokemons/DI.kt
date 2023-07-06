package ru.elvitalya.droiderhandbook.features.pokemons

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.withKey
import org.koin.core.component.get
import org.koin.dsl.module
import ru.elvitalya.droiderhandbook.core.ComponentFactory
import ru.elvitalya.droiderhandbook.core.network.NetworkApiFactory
import ru.elvitalya.droiderhandbook.features.pokemons.data.PokemonApi
import ru.elvitalya.droiderhandbook.features.pokemons.data.PokemonRepository
import ru.elvitalya.droiderhandbook.features.pokemons.data.PokemonRepositoryImpl
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonId
import ru.elvitalya.droiderhandbook.features.pokemons.ui.PokemonsComponent
import ru.elvitalya.droiderhandbook.features.pokemons.ui.RealPokemonsComponent
import ru.elvitalya.droiderhandbook.features.pokemons.ui.details.PokemonDetailsComponent
import ru.elvitalya.droiderhandbook.features.pokemons.ui.details.RealPokemonDetailsComponent
import ru.elvitalya.droiderhandbook.features.pokemons.ui.list.PokemonListComponent
import ru.elvitalya.droiderhandbook.features.pokemons.ui.list.RealPokemonListComponent

val pokemonsModule = module {
    single<PokemonApi> { get<NetworkApiFactory>().unauthorizedKtorfit.create() }
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createPokemonsComponent(
    componentContext: ComponentContext
): PokemonsComponent {
    return RealPokemonsComponent(componentContext, get())
}

fun ComponentFactory.createPokemonListComponent(
    componentContext: ComponentContext,
    onOutput: (PokemonListComponent.Output) -> Unit
): PokemonListComponent {
    val pokemonsByTypeReplica = get<PokemonRepository>().pokemonsByTypeReplica
    return RealPokemonListComponent(componentContext, onOutput, pokemonsByTypeReplica, get())
}

fun ComponentFactory.createPokemonDetailsComponent(
    componentContext: ComponentContext,
    pokemonId: PokemonId
): PokemonDetailsComponent {
    val pokemonReplica = get<PokemonRepository>().pokemonByIdReplica.withKey(pokemonId)
    return RealPokemonDetailsComponent(componentContext, pokemonReplica, get())
}
