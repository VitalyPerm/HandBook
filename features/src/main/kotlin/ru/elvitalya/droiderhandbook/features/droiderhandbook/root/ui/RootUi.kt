package ru.elvitalya.droiderhandbook.features.droiderhandbook.root.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import ru.elvitalya.droiderhandbook.features.droiderhandbook.favorite.FavoriteUi
import ru.elvitalya.droiderhandbook.features.droiderhandbook.search.SearchUi
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.SectionUi
import ru.elvitalya.droiderhandbook.features.droiderhandbook.test.TestUi

@Composable
fun DroiderHandBookRootUi(
    component: DroiderHandBookRootComponent,
    modifier: Modifier = Modifier
) {

    val childStack by component.childStack.collectAsState()
    val activeComponent = childStack.active.instance

    Column(modifier = modifier) {
        Children(
            stack = childStack,
            modifier = Modifier.weight(1f),
            animation = stackAnimation(fade())
        ) {
            when (val child = it.instance) {
                is DroiderHandBookRootComponent.Child.Favorite -> FavoriteUi(child.component)
                is DroiderHandBookRootComponent.Child.Search -> SearchUi(child.component)
                is DroiderHandBookRootComponent.Child.Section -> SectionUi(child.component)
                is DroiderHandBookRootComponent.Child.Test -> TestUi(child.component)
            }
        }

        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BottomNavigationItem(
                selected = activeComponent is DroiderHandBookRootComponent.Child.Section,
                onClick = component::onSectionClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "List"
                    )
                }
            )

            BottomNavigationItem(
                selected = activeComponent is DroiderHandBookRootComponent.Child.Favorite,
                onClick = component::onFavoriteClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite"
                    )
                }
            )

            BottomNavigationItem(
                selected = activeComponent is DroiderHandBookRootComponent.Child.Search,
                onClick = component::onSearchClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            )

            BottomNavigationItem(
                selected = activeComponent is DroiderHandBookRootComponent.Child.Test,
                onClick = component::onTestClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info"
                    )
                }
            )
        }
    }

}