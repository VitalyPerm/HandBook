package ru.elvitalya.droiderhandbook.features.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.elvitalya.droiderhandbook.features.auth.ui.login.LoginUI
import ru.elvitalya.droiderhandbook.features.auth.ui.registration.RegistrationUi
import ru.elvitalya.droiderhandbook.features.auth.ui.select_method.SelectMethodUi

@Composable
fun AuthsUI(
    component: AuthsComponent
) {
    val childStack by component.childStack.collectAsState()

    Children(stack = childStack) { child ->
        when (val instance = child.instance) {
            is AuthsComponent.Child.Login -> LoginUI(instance.component)
            is AuthsComponent.Child.Registration -> RegistrationUi(instance.component)
            is AuthsComponent.Child.SelectMethod -> SelectMethodUi(instance.component)
        }

    }


}