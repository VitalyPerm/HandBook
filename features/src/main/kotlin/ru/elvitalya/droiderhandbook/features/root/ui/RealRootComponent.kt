package ru.elvitalya.droiderhandbook.features.root.ui

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.parcelize.Parcelize
import ru.elvitalya.droiderhandbook.core.ComponentFactory
import ru.elvitalya.droiderhandbook.core.createMessageComponent
import ru.elvitalya.droiderhandbook.core.utils.toStateFlow
import ru.elvitalya.droiderhandbook.features.pokemons.createPokemonsComponent
import ru.elvitalya.droiderhandbook.features.sections.createSectionsComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Sections,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    override val messageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        is ChildConfig.Pokemons -> {
            RootComponent.Child.Pokemons(
                componentFactory.createPokemonsComponent(componentContext)
            )
        }

       is ChildConfig.Sections -> RootComponent.Child.Sections(
            componentFactory.createSectionsComponent(componentContext)
        )
    }

    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Pokemons : ChildConfig

        @Parcelize
        object Sections: ChildConfig
    }
}
