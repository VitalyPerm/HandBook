package ru.elvitalya.droiderhandbook.features.auth

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.elvitalya.droiderhandbook.core.ComponentFactory
import ru.elvitalya.droiderhandbook.features.auth.ui.AuthsComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.RealAuthsComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.login.LoginComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.login.RealLoginComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.registration.RealRegistrationComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.registration.RegistrationComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.select_method.RealSelectMethodComponent
import ru.elvitalya.droiderhandbook.features.auth.ui.select_method.SelectMethodComponent

val authsModule = module {

}

fun ComponentFactory.createAuthsComponent(
    componentContext: ComponentContext
): AuthsComponent = RealAuthsComponent(componentContext, get())

fun ComponentFactory.createSelectAuthMethodComponent(
    componentContext: ComponentContext,
    toLogin: () -> Unit,
    toRegistration: () -> Unit
): SelectMethodComponent = RealSelectMethodComponent(componentContext, toLogin, toRegistration)

fun ComponentFactory.createLoginComponent(
    componentContext: ComponentContext,
): LoginComponent = RealLoginComponent(componentContext)

fun ComponentFactory.createRegistrationComponent(
    componentContext: ComponentContext
): RegistrationComponent = RealRegistrationComponent(componentContext)