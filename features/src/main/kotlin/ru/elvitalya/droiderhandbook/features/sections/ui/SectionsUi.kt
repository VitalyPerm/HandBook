package ru.elvitalya.droiderhandbook.features.sections.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.elvitalya.droiderhandbook.features.sections.ui.details.QuestionDetailsUi
import ru.elvitalya.droiderhandbook.features.sections.ui.list.QuestionsListUi

@Composable
fun SectionsUi(
    component: SectionsComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.collectAsState()

    Children(childStack, modifier) { child ->
        when (val instance = child.instance) {
            is SectionsComponent.Child.Details -> QuestionDetailsUi(instance.component)
            is SectionsComponent.Child.List -> QuestionsListUi(instance.component)
        }

    }
}