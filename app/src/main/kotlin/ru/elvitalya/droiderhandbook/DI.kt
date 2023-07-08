package ru.elvitalya.droiderhandbook

import ru.elvitalya.droiderhandbook.core.coreModule
import ru.elvitalya.droiderhandbook.features.pokemons.pokemonsModule
import ru.elvitalya.droiderhandbook.features.sections.sectionsModule

val allModules = listOf(
    coreModule(
        pokemonUrl = BuildConfig.BACKEND_URL,
        droiderHandBookUrl = BuildConfig.DROIDER_HANDBOOK_URL
    ),
    pokemonsModule,
    sectionsModule
)