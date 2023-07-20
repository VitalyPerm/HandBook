package ru.elvitalya.droiderhandbook.features.droiderhandbook.section.ui

import QuestionType
import QuestionTypeId
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.elvitalya.droiderhandbook.core.widget.EmptyPlaceholder
import ru.elvitalya.droiderhandbook.core.widget.RefreshingProgress
import ru.elvitalya.droiderhandbook.core.widget.SwipeRefreshLceWidget
import ru.elvitalya.droiderhandbook.features.R
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.domain.Question

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SectionUi(
    component: SectionComponent,
    modifier: Modifier = Modifier
) {
    val questionsState by component.questionsState.collectAsState()
    val selectedTypeId by component.selectedTypeId.collectAsState()


    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var selectedQuestion by rememberSaveable { mutableStateOf(Question.EMPTY) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = selectedQuestion.title,
                        fontSize = 22.sp,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(text = selectedQuestion.text)
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
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
                                onQuestionClick = {
                                    scope.launch {
                                        selectedQuestion = it
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            )
                        } else EmptyPlaceholder(description = stringResource(R.string.questions_empty_description))
                        RefreshingProgress(refreshing)
                    }
                }
            }
        )


    }
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

            if (question !== questions.lastOrNull()) Divider()

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