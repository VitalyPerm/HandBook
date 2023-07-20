package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.details

import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question

interface QuestionDetailsComponent {

    val questionState: StateFlow<Question>

    fun onRetryClick()

    fun onRefresh()
}