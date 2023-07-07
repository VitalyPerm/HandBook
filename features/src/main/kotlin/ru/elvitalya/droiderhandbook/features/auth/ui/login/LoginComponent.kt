package ru.elvitalya.droiderhandbook.features.auth.ui.login

import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.core.utils.ViewState

interface LoginComponent {

    val email: StateFlow<String>
    fun onEmailInputChanged(emailInput: String)

    val password: StateFlow<String>
    fun onPassInputChanged(passwordInput: String)

    val authButtonEnabled: StateFlow<Boolean>
    fun checkButtonAuthEnabled()

    fun login()

    val errorMessage: StateFlow<String>

    val viewState: StateFlow<ViewState>

}