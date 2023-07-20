package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.list

import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.QuestionType
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.QuestionTypeId

interface QuestionListComponent {

    val types: List<QuestionType>

    val selectedTypeId: StateFlow<QuestionTypeId>

    val questionsState: StateFlow<LoadableState<List<Question>>>

    fun onTypeClick(typeId: QuestionTypeId)

    fun onQuestionClick(question: Question)

    fun onRetryClick()

    fun onRefresh()

    sealed interface OutPut {
        data class QuestionDetailsRequested(val question: Question): OutPut
    }



}

/*
interface PokemonListComponent {

    val types: List<PokemonType>

    val selectedTypeId: StateFlow<PokemonTypeId>

    val pokemonsState: StateFlow<LoadableState<List<Pokemon>>>

    fun onTypeClick(typeId: PokemonTypeId)

    fun onPokemonClick(pokemonId: PokemonId)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class PokemonDetailsRequested(val pokemonId: PokemonId) : Output
    }
}

 */