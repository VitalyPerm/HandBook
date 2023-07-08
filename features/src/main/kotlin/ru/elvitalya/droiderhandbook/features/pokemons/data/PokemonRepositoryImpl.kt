package ru.elvitalya.droiderhandbook.features.pokemons.data

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedPhysicalReplica
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.elvitalya.droiderhandbook.core.network.NetworkApiFactory
import ru.elvitalya.droiderhandbook.features.pokemons.data.dto.toDomain
import ru.elvitalya.droiderhandbook.features.pokemons.domain.DetailedPokemon
import ru.elvitalya.droiderhandbook.features.pokemons.domain.Pokemon
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonId
import ru.elvitalya.droiderhandbook.features.pokemons.domain.PokemonTypeId
import kotlin.time.Duration.Companion.seconds

class PokemonRepositoryImpl(
    replicaClient: ReplicaClient,
    networkApiFactory: NetworkApiFactory,
) : PokemonRepository {

    val api = networkApiFactory.unauthorizedKtorfit.create<PokemonApi>()

    override val pokemonsByTypeReplica: KeyedPhysicalReplica<PokemonTypeId, List<Pokemon>> =
        replicaClient.createKeyedReplica(
            name = "pokemonsByType",
            childName = { typeId -> "typeId = ${typeId.value}" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 10.seconds,
                    clearTime = 60.seconds
                )
            }
        ) { pokemonTypeId ->
            api.getPokemonsByType(pokemonTypeId.value).toDomain()
        }

    override val pokemonByIdReplica: KeyedPhysicalReplica<PokemonId, DetailedPokemon> =
        replicaClient.createKeyedReplica(
            name = "pokemonById",
            childName = { pokemonId -> "pokemonId = ${pokemonId.value}" },
            settings = KeyedReplicaSettings(maxCount = 5),
            childSettings = {
                ReplicaSettings(staleTime = 10.seconds)
            }
        ) { pokemonId ->
            api.getPokemonById(pokemonId.value).toDomain()
        }
}