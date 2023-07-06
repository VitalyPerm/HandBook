package ru.elvitalya.droiderhandbook.features.auth.ui.login

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState

class RealLoginComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, LoginComponent {

    override val loginState =
        MutableStateFlow<LoadableState<Boolean>>(LoadableState(loading = false))

    override val email = MutableStateFlow("")

    override val password = MutableStateFlow("")

    override val authButtonEnabled = MutableStateFlow(false)

    override fun onEmailInputChanged(emailInput: String) {
        email.value = emailInput
        checkButtonAuthEnabled()
    }

    override fun onPassInputChanged(passwordInput: String) {
        password.value = passwordInput
        checkButtonAuthEnabled()
    }

    override fun checkButtonAuthEnabled() {
        authButtonEnabled.value =
            email.value.length > 5 && email.value.contains("@") && password.value.length > 6
    }

    override fun login() {
        // todo api call
    }
}