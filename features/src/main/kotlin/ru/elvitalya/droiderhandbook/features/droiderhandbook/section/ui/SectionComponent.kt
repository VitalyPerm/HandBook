package ru.elvitalya.droiderhandbook.features.droiderhandbook.section.ui

import QuestionType
import QuestionTypeId
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.domain.Question

interface SectionComponent {

    val types: List<QuestionType>

    val selectedTypeId: StateFlow<QuestionTypeId>

    val questionsState: StateFlow<LoadableState<List<Question>>>

    fun onTypeClick(typeId: QuestionTypeId)

    fun onQuestionClick(question: Question)

    fun onRetryClick()

    fun onRefresh()

}