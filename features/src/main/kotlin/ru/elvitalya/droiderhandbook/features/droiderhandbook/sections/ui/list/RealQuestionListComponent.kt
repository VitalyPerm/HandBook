package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.list

import QuestionType
import QuestionTypeId
import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed.keepPreviousData
import ru.elvitalya.droiderhandbook.core.error_handling.ErrorHandler
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.core.utils.observe
import ru.elvitalya.droiderhandbook.core.utils.persistent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.domain.Question

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

class RealQuestionListComponent(
    componentContext: ComponentContext,
    private val onOutput: (QuestionListComponent.OutPut) -> Unit,
    private val questionsByTypeReplica: KeyedReplica<QuestionTypeId, List<Question>>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, QuestionListComponent {


    override val types = listOf(
        QuestionType.Java,
        QuestionType.Kotlin,
        QuestionType.Android,
        QuestionType.Coroutines,
    )

    override val selectedTypeId = MutableStateFlow(types[0].id)


    override val questionsState = questionsByTypeReplica
        .keepPreviousData()
        .observe(
            lifecycle,
            errorHandler,
            key = selectedTypeId
        )

    init {
        persistent(
            save = { PersistentState(selectedTypeId.value) },
            restore = { state -> selectedTypeId.value = state.selectedTypeId }
        )
    }

    override fun onTypeClick(typeId: QuestionTypeId) {
        selectedTypeId.value = typeId
    }

    override fun onQuestionClick(question: Question) {
        onOutput(QuestionListComponent.OutPut.QuestionDetailsRequested(question))
    }

    override fun onRetryClick() {
        questionsByTypeReplica.refresh(selectedTypeId.value)
    }

    override fun onRefresh() {
        questionsByTypeReplica.refresh(selectedTypeId.value)
    }

    @Parcelize
    private data class PersistentState(
        val selectedTypeId: QuestionTypeId
    ) : Parcelable
}