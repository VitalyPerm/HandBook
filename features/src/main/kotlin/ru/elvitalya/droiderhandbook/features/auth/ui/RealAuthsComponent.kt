package ru.elvitalya.droiderhandbook.features.auth.ui

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.parcelize.Parcelize
import ru.elvitalya.droiderhandbook.core.utils.toStateFlow
import ru.elvitalya.droiderhandbook.features.pokemons.ui.RealPokemonsComponent

class RealAuthsComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, AuthsComponent {

    private val navigation = StackNavigation<RealPokemonsComponent.ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = RealPokemonsComponent.ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: RealPokemonsComponent.ChildConfig,
        componentContext: ComponentContext
    ):AuthsComponent.Child = when(config) {
        is RealPokemonsComponent.ChildConfig.Details -> TODO()
        is RealPokemonsComponent.ChildConfig.List -> TODO()
    }




    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object ChooseMethod : ChildConfig

        @Parcelize
        object Login : ChildConfig

        @Parcelize
        object Registration : ChildConfig
    }

}