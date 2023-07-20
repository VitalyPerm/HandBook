package ru.elvitalya.droiderhandbook.features.droiderhandbook.root.ui

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize
import ru.elvitalya.droiderhandbook.core.ComponentFactory
import ru.elvitalya.droiderhandbook.core.utils.toStateFlow
import ru.elvitalya.droiderhandbook.features.droiderhandbook.createFavoriteComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.createSearchComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.createSectionComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.createTestComponent

class RealDroiderHandBookRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, DroiderHandBookRootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, DroiderHandBookRootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = ChildConfig.Sections,
            handleBackButton = true,
            childFactory = ::createChild
        ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): DroiderHandBookRootComponent.Child = when (config) {
        ChildConfig.Favorite -> DroiderHandBookRootComponent.Child.Favorite(
            componentFactory.createFavoriteComponent(componentContext)
        )

        ChildConfig.Search -> DroiderHandBookRootComponent.Child.Search(
            componentFactory.createSearchComponent(componentContext)
        )

        ChildConfig.Sections -> DroiderHandBookRootComponent.Child.Section(
            componentFactory.createSectionComponent(componentContext)
        )

        ChildConfig.Test -> DroiderHandBookRootComponent.Child.Test(
            componentFactory.createTestComponent(componentContext)
        )
    }

    override fun onSectionClicked() {
       navigation.bringToFront(ChildConfig.Sections)
    }

    override fun onFavoriteClicked() {
        navigation.bringToFront(ChildConfig.Favorite)
    }

    override fun onSearchClicked() {
        navigation.bringToFront(ChildConfig.Search)
    }

    override fun onTestClicked() {
        navigation.bringToFront(ChildConfig.Test)
    }

    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object Sections : ChildConfig

        @Parcelize
        object Favorite : ChildConfig

        @Parcelize
        object Search : ChildConfig

        @Parcelize
        object Test : ChildConfig
    }

}

/*
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

       is ChildConfig.DroiderHandBook -> RootComponent.Child.DroiderHandBook(
            componentFactory.createDroiderComponent(componentContext)
        )
    }

    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Pokemons : ChildConfig

        @Parcelize
        object DroiderHandBook: ChildConfig
    }
}
 */