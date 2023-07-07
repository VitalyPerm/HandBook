package ru.elvitalya.droiderhandbook.features.auth.ui.select_method

import com.arkivanov.decompose.ComponentContext

class RealSelectMethodComponent(
    componentContext: ComponentContext,
    private val toLogin: () -> Unit,
    private val toRegistration: () -> Unit
) : ComponentContext by componentContext, SelectMethodComponent {

    override fun onLoginClick() {
        toLogin()
    }

    override fun onRegistrationClick() {
        toRegistration()
    }
}