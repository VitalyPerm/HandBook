package ru.elvitalya.droiderhandbook.features.auth.ui.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.core.utils.ViewState

class RealRegistrationComponent(
    componentContext: ComponentContext,
    private val context: Context
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
        Log.d("check___", "registration called! ")
        val sherPrefs = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        sherPrefs.edit {
            putBoolean("auth", true)
        }
    }

    init {
        val sherPrefs = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        val isAuth = sherPrefs.getBoolean("auth", false)
        Toast.makeText(context, "isAuth - $isAuth", Toast.LENGTH_LONG).show()
    }
}