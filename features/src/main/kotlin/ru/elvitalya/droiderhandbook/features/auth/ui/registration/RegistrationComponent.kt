package ru.elvitalya.droiderhandbook.features.auth.ui.registration

import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.ViewState

interface RegistrationComponent {

    val email: StateFlow<String>
    fun onEmailInputChanged(emailInput: String)

    val password: StateFlow<String>
    fun onPassInputChanged(passwordInput: String)

    val authButtonEnabled: StateFlow<Boolean>
    fun checkButtonAuthEnabled()

    fun registration()

    val errorMessage: StateFlow<String>

    val viewState: StateFlow<ViewState>


}