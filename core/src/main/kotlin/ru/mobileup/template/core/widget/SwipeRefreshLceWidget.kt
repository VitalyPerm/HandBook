package ru.mobileup.template.core.widget

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import me.aartikov.replica.single.Loadable
import ru.mobileup.template.core.utils.LoadableState

/**
 * Displays Replica state ([Loadable]) with swipe-to-refresh functionality.
 *
 * Note: a value of refreshing in [content] is true only when data is refreshing and swipe gesture didn't occur.
 */
@Composable
fun <T : Any> SwipeRefreshLceWidget(
    state: LoadableState<T>,
    onRefresh: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    swipeRefreshIndicator: @Composable (state: SwipeRefreshState, refreshTrigger: Dp) -> Unit = { s, trigger ->
        SwipeRefreshIndicator(s, trigger, contentColor = MaterialTheme.colors.primaryVariant)
    },
    content: @Composable (data: T, refreshing: Boolean) -> Unit
) {

    LceWidget(
        state = state,
        onRetryClick = onRetryClick,
        modifier = modifier
    ) { data, refreshing ->
        var swipeGestureOccurred by remember { mutableStateOf(false) }

        LaunchedEffect(refreshing) {
            if (!refreshing) swipeGestureOccurred = false
        }

        val swipeRefreshState = rememberSwipeRefreshState(swipeGestureOccurred && refreshing)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                swipeGestureOccurred = true
                onRefresh()
            },
            indicator = swipeRefreshIndicator
        ) {
            content(data, refreshing = refreshing && !swipeGestureOccurred)
        }
    }
}
