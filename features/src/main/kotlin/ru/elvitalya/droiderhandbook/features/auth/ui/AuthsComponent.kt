package ru.elvitalya.droiderhandbook.features.auth.ui

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.features.auth.ui.login.LoginComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.registration.RegistrationComponent

interface AuthsComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Registration(val component: RegistrationComponent)
        class Login(val component: LoginComponent)
    }

}