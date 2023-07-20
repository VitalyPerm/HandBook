package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.QuestionType
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.QuestionTypeId

class FakeQuestionListComponent : QuestionListComponent {

    override val types: List<QuestionType> = listOf(
        QuestionType.Java,
        QuestionType.Kotlin,
        QuestionType.Coroutines,
        QuestionType.Android
    )

    override val selectedTypeId: StateFlow<QuestionTypeId> = MutableStateFlow(types[0].id)

    override val questionsState: StateFlow<LoadableState<List<Question>>> = MutableStateFlow(
        LoadableState(
            loading = false,
            data = List(100) {
                Question(number = it, title = "title - $it", text = "text - $it", picUrl = "")
            }
        )
    )

    override fun onTypeClick(typeId: QuestionTypeId) = Unit

    override fun onQuestionClick(question: Question) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}