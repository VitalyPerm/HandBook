package ru.elvitalya.droiderhandbook.features.sections.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.elvitalya.droiderhandbook.core.theme.white
import ru.elvitalya.droiderhandbook.features.AppBar

@Composable
fun QuestionDetailsUi(
    component: QuestionDetailsComponent,
    modifier: Modifier = Modifier
) {
    val question by component.questionState.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(white)
        ) {
            AppBar(
                title = question.title,
                onIconClick = {},
                icon = Icons.Default.Close
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                SelectionContainer(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = question.text,
                        lineHeight = 24.sp,
                        fontSize = 20.sp
                    )
                }

            }

        }
    }
}