package ru.elvitalya.droiderhandbook.features.root.ui

import ru.elvitalya.droiderhandbook.core.message.ui.FakeMessageComponent
import ru.elvitalya.droiderhandbook.core.utils.createFakeChildStackStateFlow
import ru.elvitalya.droiderhandbook.features.pokemons.ui.FakePokemonsComponent

class FakeRootComponent : RootComponent {

    override val childStack = createFakeChildStackStateFlow(
        RootComponent.Child.Pokemons(FakePokemonsComponent())
    )

    override val messageComponent = FakeMessageComponent()
}
