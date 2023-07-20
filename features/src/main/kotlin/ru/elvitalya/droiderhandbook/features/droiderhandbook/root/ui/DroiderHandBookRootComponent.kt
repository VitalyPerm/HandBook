package ru.elvitalya.droiderhandbook.features.droiderhandbook.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.elvitalya.droiderhandbook.features.droiderhandbook.favorite.FavoriteComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.search.SearchComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.SectionComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.test.TestComponent

interface DroiderHandBookRootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    fun onSectionClicked()
    fun onFavoriteClicked()
    fun onSearchClicked()
    fun onTestClicked()

    sealed interface Child {
        class Section(val component: SectionComponent): Child
        class Favorite(val component: FavoriteComponent): Child
        class Search(val component: SearchComponent): Child
        class Test(val component: TestComponent): Child
    }
}