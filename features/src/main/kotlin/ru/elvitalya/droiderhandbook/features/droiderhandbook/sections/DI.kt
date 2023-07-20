package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections

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
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.RealSectionComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.SectionComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.data.QuestionRepository
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.data.QuestionRepositoryImpl
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.data.QuestionsApi
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.details.QuestionDetailsComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.details.RealQuestionDetailsComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.list.QuestionListComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.ui.list.RealQuestionListComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.test.RealTestComponent
import ru.elvitalya.droiderhandbook.features.droiderhandbook.test.TestComponent

val sectionsModule = module {
    single<QuestionsApi> { get<DroiderHandBookApiFactory>().ktorfit.create() }
    single<QuestionRepository> { QuestionRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createQuestionListComponent(
    componentContext: ComponentContext,
    onOutPut: (QuestionListComponent.OutPut) -> Unit
): QuestionListComponent {
    val questionsByTypeReplica = get<QuestionRepository>().questionsByTypeReplica
    return RealQuestionListComponent(componentContext, onOutPut, questionsByTypeReplica, get())
}

fun ComponentFactory.createQuestionDetailsComponent(
    componentContext: ComponentContext,
    question: Question
): QuestionDetailsComponent {
    return RealQuestionDetailsComponent(componentContext, question)
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
): SectionComponent = RealSectionComponent(componentContext)

fun ComponentFactory.createSearchComponent(
    componentContext: ComponentContext
): SearchComponent = RealSearchComponent(componentContext)

fun ComponentFactory.createFavoriteComponent(
    componentContext: ComponentContext
): FavoriteComponent = RealFavoriteComponent(componentContext)

