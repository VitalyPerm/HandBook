package ru.elvitalya.droiderhandbook.features.droiderhandbook.test

import com.arkivanov.decompose.ComponentContext

class RealTestComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, TestComponent {
}