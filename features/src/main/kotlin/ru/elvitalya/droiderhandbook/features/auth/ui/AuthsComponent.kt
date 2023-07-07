package ru.elvitalya.droiderhandbook.features.auth.ui

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.features.auth.ui.login.LoginComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.registration.RegistrationComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.select_method.SelectMethodComponent

interface AuthsComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Registration(val component: RegistrationComponent): Child
        class Login(val component: LoginComponent): Child
        class SelectMethod(val component: SelectMethodComponent): Child
    }

}