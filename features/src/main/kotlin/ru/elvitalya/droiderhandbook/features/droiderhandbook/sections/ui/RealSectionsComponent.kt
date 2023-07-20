package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import kotlinx.parcelize.Parcelize
import ru.elvitalya.droiderhandbook.core.ComponentFactory
import ru.elvitalya.droiderhandbook.core.utils.toStateFlow
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.createQuestionDetailsComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.createQuestionListComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.list.QuestionListComponent

class RealSectionsComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, SectionsComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): SectionsComponent.Child = when (config) {
        is ChildConfig.Details -> SectionsComponent.Child.Details(
            componentFactory.createQuestionDetailsComponent(
                componentContext,
                config.question
            )
        )

        ChildConfig.List -> SectionsComponent.Child.List(
            componentFactory.createQuestionListComponent(
                componentContext,
                ::onQuestionListOutput
            )
        )
    }

    private fun onQuestionListOutput(output: QuestionListComponent.OutPut) {
        when (output) {
            is QuestionListComponent.OutPut.QuestionDetailsRequested -> {
                navigation.push(ChildConfig.Details(output.question))
            }
        }
    }


    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object List : ChildConfig

        @Parcelize
        data class Details(val question: Question) : ChildConfig
    }
}