package ru.elvitalya.droiderhandbook.core.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.elvitalya.droiderhandbook.core.R
import ru.elvitalya.droiderhandbook.core.theme.AppTheme

@Composable
fun ErrorPlaceholder(
    errorMessage: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.body2
        )
        TextButton(
            onClick = onRetryClick
        ) {
            Text(
                text = stringResource(R.string.common_retry).uppercase()
            )
        }
    }
}

@Preview
@Composable
fun ErrorPlaceholderPreview() {
    AppTheme {
        ErrorPlaceholder(
            errorMessage = "Error message",
            onRetryClick = {}
        )
    }
}