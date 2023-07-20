package ru.elvitalya.droiderhandbook.features.droiderhandbook.search

import com.arkivanov.decompose.ComponentContext

class RealSearchComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, SearchComponent {
}