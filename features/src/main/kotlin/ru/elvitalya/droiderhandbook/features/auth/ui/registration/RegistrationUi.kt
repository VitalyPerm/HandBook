package ru.elvitalya.droiderhandbook.features.auth.ui.registration

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.elvitalya.droiderhandbook.core.utils.ViewState
import ru.elvitalya.droiderhandbook.features.AppBar
import ru.elvitalya.droiderhandbook.features.LoadingBanner
import ru.elvitalya.droiderhandbook.features.R
import ru.elvitalya.droiderhandbook.features.auth.ui.AuthErrorCard
import ru.elvitalya.droiderhandbook.features.auth.ui.LoginPassInput

@Composable
fun RegistrationUi(
    component: RegistrationComponent,
    modifier: Modifier = Modifier
) {
    val state by component.viewState.collectAsState()
    val email by component.email.collectAsState()
    val password by component.password.collectAsState()
    val authButtonEnabled by component.authButtonEnabled.collectAsState()
    val errorMessage by component.errorMessage.collectAsState()


    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Screen(
            state = state,
            email = email,
            onEmailInputChanged = component::onEmailInputChanged,
            password = password,
            onPassInputChanged = component::onPassInputChanged,
            onRegistrationClick = component::registration,
            errorMessage = errorMessage,
            authButtonEnabled = authButtonEnabled
        )
    }
}

@Composable
private fun Screen(
    state: ViewState,
    email: String,
    onEmailInputChanged: (String) -> Unit,
    password: String,
    onPassInputChanged: (String) -> Unit,
    onRegistrationClick: () -> Unit,
    errorMessage: String,
    authButtonEnabled: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .navigationBarsPadding()
            .imePadding()
    ) {

        AppBar(
            title = stringResource(R.string.auth_registration),
            onIconClick = onRegistrationClick,
            icon = Icons.Default.Close
        )

        Spacer(modifier = Modifier.height(16.dp))

        Crossfade(targetState = state, label = "") { state ->
            if (state is ViewState.Loading) LoadingBanner()
            else {
                Column {
                    LoginPassInput(
                        email = email,
                        onEmailInputChanged = onEmailInputChanged,
                        password = password,
                        onPassInputChanged = onPassInputChanged,
                        onAuthClick = onRegistrationClick,
                        authButtonEnabled = authButtonEnabled
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(visible = errorMessage.isNotBlank()) {
                        AuthErrorCard(message = errorMessage)
                    }
                }
            }

        }

    }
}