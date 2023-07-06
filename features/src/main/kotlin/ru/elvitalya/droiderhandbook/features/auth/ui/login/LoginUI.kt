package ru.elvitalya.droiderhandbook.features.auth.ui.login

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
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.features.AppBar
import ru.elvitalya.droiderhandbook.features.LoadingBanner
import ru.elvitalya.droiderhandbook.features.R
import ru.elvitalya.droiderhandbook.features.auth.ui.AuthErrorCard
import ru.elvitalya.droiderhandbook.features.auth.ui.LoginPassInput

@Composable
fun LoginUI(
    loginComponent: LoginComponent,
    modifier: Modifier = Modifier
) {
    val state by loginComponent.loginState.collectAsState()
    val email by loginComponent.email.collectAsState()
    val password by loginComponent.password.collectAsState()
    val authButtonEnabled by loginComponent.authButtonEnabled.collectAsState()


    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Screen(
            state = state,
            email = email,
            onEmailInputChanged = loginComponent::onEmailInputChanged,
            password = password,
            onPassInputChanged = loginComponent::onPassInputChanged,
            onAuthClick = loginComponent::login,
            errorMessage = "",
            authButtonEnabled = authButtonEnabled
        )
    }
}

@Composable
private fun Screen(
    state: LoadableState<Boolean>,
    email: String,
    onEmailInputChanged: (String) -> Unit,
    password: String,
    onPassInputChanged: (String) -> Unit,
    onAuthClick: () -> Unit,
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
            onIconClick = onAuthClick,
            icon = Icons.Default.Close
        )

        Spacer(modifier = Modifier.height(16.dp))

        Crossfade(targetState = state, label = "") { state ->
            if (state.loading) LoadingBanner()
            else {
                Column {
                    LoginPassInput(
                        email = email,
                        onEmailInputChanged = onEmailInputChanged,
                        password = password,
                        onPassInputChanged = onPassInputChanged,
                        onAuthClick = onAuthClick,
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