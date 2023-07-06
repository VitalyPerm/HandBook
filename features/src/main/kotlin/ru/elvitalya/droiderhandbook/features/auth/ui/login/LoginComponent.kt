package ru.elvitalya.droiderhandbook.features.auth.ui.login

import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState

interface LoginComponent {

    val loginState: StateFlow<LoadableState<Boolean>>

    val email: StateFlow<String>

    val password: StateFlow<String>

    val authButtonEnabled: StateFlow<Boolean>

    fun onEmailInputChanged(emailInput: String)

    fun onPassInputChanged(passwordInput: String)

    fun checkButtonAuthEnabled()

    fun login()

}