package ru.elvitalya.droiderhandbook.features.droiderhandbook

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.elvitalya.droiderhandbook.core.ComponentFactory
import ru.elvitalya.droiderhandbook.core.network.DroiderHandBookApiFactory
import ru.elvitalya.droiderhandbook.features.droiderhandbook.favorite.FavoriteComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.favorite.RealFavoriteComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.root.ui.DroiderHandBookRootComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.root.ui.RealDroiderHandBookRootComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.search.RealSearchComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.search.SearchComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.data.QuestionRepository
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.data.QuestionRepositoryImpl
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.data.QuestionsApi
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.ui.RealSectionComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.ui.SectionComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.test.RealTestComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.test.TestComponent

val sectionsModule = module {
    single<QuestionsApi> { get<DroiderHandBookApiFactory>().ktorfit.create() }
    single<QuestionRepository> { QuestionRepositoryImpl(get(), get()) }
    single<QuestionRepository> { QuestionRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createDroiderHandBookRootComponent(
    componentContext: ComponentContext
): DroiderHandBookRootComponent =
    RealDroiderHandBookRootComponent(componentContext, get())

fun ComponentFactory.createTestComponent(
    componentContext: ComponentContext
): TestComponent = RealTestComponent(componentContext)

fun ComponentFactory.createSectionComponent(
    componentContext: ComponentContext
): SectionComponent {
    val questionsByTypeReplica = get<QuestionRepository>().questionsByTypeReplica
    return RealSectionComponent(componentContext, questionsByTypeReplica, get())
}

fun ComponentFactory.createSearchComponent(
    componentContext: ComponentContext
): SearchComponent = RealSearchComponent(componentContext)

fun ComponentFactory.createFavoriteComponent(
    componentContext: ComponentContext
): FavoriteComponent = RealFavoriteComponent(componentContext)

