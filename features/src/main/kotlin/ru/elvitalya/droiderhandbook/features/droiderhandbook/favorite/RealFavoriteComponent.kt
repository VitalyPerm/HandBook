package ru.elvitalya.droiderhandbook.features.droiderhandbook.favorite

import com.arkivanov.decompose.ComponentContext

class RealFavoriteComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, FavoriteComponent {
}