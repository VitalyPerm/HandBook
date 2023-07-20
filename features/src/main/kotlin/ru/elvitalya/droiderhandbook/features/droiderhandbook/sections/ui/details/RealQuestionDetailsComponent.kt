package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.details

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question

class RealQuestionDetailsComponent(
    componentContext: ComponentContext,
    private val question: Question
) : ComponentContext by componentContext, QuestionDetailsComponent {

    override val questionState = MutableStateFlow(question)

    override fun onRetryClick() {

    }

    override fun onRefresh() {

    }

}