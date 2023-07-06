package ru.elvitalya.droiderhandbook.features.pokemons.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonType
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonTypeId

@Serializable
class PokemonTypeResponse(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
class PokemonTypeWrapperResponse(
    @SerialName("type") val type: PokemonTypeResponse
)

fun PokemonTypeResponse.toDomain(): PokemonType {
    return PokemonType(
        id = PokemonTypeId(parseId(url)),
        name = formatName(name)
    )
}