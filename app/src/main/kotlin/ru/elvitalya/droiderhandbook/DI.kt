package ru.elvitalya.droiderhandbook

import ru.elvitalya.droiderhandbook.core.coreModule
import ru.elvitalya.droiderhandbook.features.pokemons.pokemonsModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    pokemonsModule
)