package ru.elvitalya.droiderhandbook.features.auth.ui.login

import android.content.Context
import androidx.core.content.edit
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.LoadableState
import ru.elvitalya.droiderhandbook.core.utils.ViewState

class RealLoginComponent(
    componentContext: ComponentContext,
    private val context: Context
) : ComponentContext by componentContext, LoginComponent {


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

    override fun login() {
        val sherPrefs = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        sherPrefs.edit {
            putBoolean("auth", true)
        }
    }
}