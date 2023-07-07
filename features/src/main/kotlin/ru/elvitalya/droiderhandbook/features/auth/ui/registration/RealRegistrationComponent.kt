package ru.elvitalya.droiderhandbook.features.auth.ui.registration

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.ViewState

class RealRegistrationComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, RegistrationComponent {

    override val errorMessage = MutableStateFlow("")

    override val viewState = MutableStateFlow<ViewState>(ViewState.Content)

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

    override fun registration() {
        // todo api call
    }
}