package ru.elvitalya.droiderhandbook.features.root

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.elvitalya.droiderhandbook.features.root.ui.RealRootComponent
import ru.elvitalya.droiderhandbook.features.root.ui.RootComponent
import ru.elvitalya.droiderhandbook.core.ComponentFactory

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(componentContext, get())
}