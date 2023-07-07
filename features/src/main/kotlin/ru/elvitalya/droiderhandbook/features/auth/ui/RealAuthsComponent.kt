package ru.elvitalya.droiderhandbook.features.auth.ui

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import kotlinx.parcelize.Parcelize
import ru.elvitalya.droiderhandbook.core.ComponentFactory
import ru.elvitalya.droiderhandbook.core.utils.toStateFlow
import ru.elvitalya.droiderhandbook.features.auth.createLoginComponent
import ru.elvitalya.droiderhandbook.features.auth.createRegistrationComponent
import ru.elvitalya.droiderhandbook.features.auth.createSelectAuthMethodComponent

class RealAuthsComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, AuthsComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.SelectMethod,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): AuthsComponent.Child = when (config) {
        ChildConfig.Login -> AuthsComponent.Child.Login(
            componentFactory.createLoginComponent(componentContext)
        )

        ChildConfig.Registration -> AuthsComponent.Child.Registration(
            componentFactory.createRegistrationComponent(componentContext)
        )

        ChildConfig.SelectMethod -> AuthsComponent.Child.SelectMethod(
            componentFactory.createSelectAuthMethodComponent(
                componentContext = componentContext,
                toLogin = { navigation.push(ChildConfig.Login) },
                toRegistration = { navigation.push(ChildConfig.Registration) }
            )
        )
    }
    //  navigation.push(ChildConfig.Details(output.pokemonId))


    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object SelectMethod : ChildConfig

        @Parcelize
        object Login : ChildConfig

        @Parcelize
        object Registration : ChildConfig
    }

}