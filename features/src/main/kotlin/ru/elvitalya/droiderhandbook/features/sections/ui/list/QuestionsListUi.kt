package ru.elvitalya.droiderhandbook.features.sections.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.elvitalya.droiderhandbook.core.widget.EmptyPlaceholder
import ru.elvitalya.droiderhandbook.core.widget.RefreshingProgress
import ru.elvitalya.droiderhandbook.core.widget.SwipeRefreshLceWidget
import ru.elvitalya.droiderhandbook.features.R
import ru.elvitalya.droiderhandbook.features.sections.domain.Question
import ru.elvitalya.droiderhandbook.features.sections.domain.QuestionType
import ru.elvitalya.droiderhandbook.features.sections.domain.QuestionTypeId

@Composable
fun QuestionsListUi(
    component: QuestionListComponent,
    modifier: Modifier = Modifier
) {
    val questionsState by component.questionsState.collectAsState()
    val selectedTypeId by component.selectedTypeId.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            QuestionTypesRow(
                types = component.types,
                selectedTypeId = selectedTypeId,
                onTypeClick = component::onTypeClick
            )
            SwipeRefreshLceWidget(
                state = questionsState,
                onRefresh = component::onRefresh,
                onRetryClick = component::onRetryClick
            ) { questions, refreshing ->
                if (questions.isNotEmpty()) {
                    QuestionListContent(
                        questions = questions,
                        onQuestionClick = component::onQuestionClick
                    )
                } else {
                    EmptyPlaceholder(description = stringResource(R.string.questions_empty_description))
                }
                RefreshingProgress(refreshing)
            }
        }

    }

}

@Composable
private fun QuestionListContent(
    questions: List<Question>,
    onQuestionClick: (Question) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            items = questions,
            key = { it.number }
        ) { question ->
            QuestionItem(
                question = question,
                onClick = { onQuestionClick(question) }
            )

            if (question !== questions.lastOrNull()) {
                Divider()
            }
        }
    }
}

@Composable
private fun QuestionItem(
    question: Question,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth(),
        text = question.title
    )
}

@Composable
private fun QuestionTypesRow(
    types: List<QuestionType>,
    selectedTypeId: QuestionTypeId,
    onTypeClick: (QuestionTypeId) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background,
        elevation = 4.dp
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                text = "Разделы",
                style = MaterialTheme.typography.h6
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                types.forEach {
                    QuestionTypeItem(
                        type = it,
                        isSelected = it.id == selectedTypeId,
                        onClick = { onTypeClick(it.id) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun QuestionTypeItem(
    type: QuestionType,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier,
        onClick = { onClick?.invoke() },
        enabled = onClick != null,
        shape = RoundedCornerShape(48.dp),
        color = when (isSelected) {
            true -> MaterialTheme.colors.primary
            else -> MaterialTheme.colors.surface
        },
        elevation = 6.dp
    ) {
        Text(
            text = type.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    QuestionsListUi(FakeQuestionListComponent())
}