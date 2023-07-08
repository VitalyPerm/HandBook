package ru.elvitalya.droiderhandbook.features.sections.ui

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.features.sections.ui.details.QuestionDetailsComponent
import ru.elvitalya.droiderhandbook.features.sections.ui.list.QuestionListComponent

interface SectionsComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class List(val component: QuestionListComponent) : Child
        class Details(val component: QuestionDetailsComponent): Child
    }

}