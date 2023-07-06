package ru.elvitalya.droiderhandbook

val allModules = listOf(
    ru.elvitalya.droiderhandbook.core.coreModule(BuildConfig.BACKEND_URL),
    ru.elvitalya.droiderhandbook.features.pokemons.pokemonsModule
)