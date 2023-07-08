package ru.elvitalya.droiderhandbook.features.sections

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.elvitalya.droiderhandbook.core.ComponentFactory
import ru.elvitalya.droiderhandbook.core.network.DroiderHandBookApiFactory
import ru.elvitalya.droiderhandbook.features.sections.data.QuestionRepository
import ru.elvitalya.droiderhandbook.features.sections.data.QuestionRepositoryImpl
import ru.elvitalya.droiderhandbook.features.sections.data.QuestionsApi
import ru.elvitalya.droiderhandbook.features.sections.domain.Question
import ru.elvitalya.droiderhandbook.features.sections.ui.details.QuestionDetailsComponent
import ru.elvitalya.droiderhandbook.features.sections.ui.details.RealQuestionDetailsComponent
import ru.elvitalya.droiderhandbook.features.sections.ui.list.QuestionListComponent
import ru.elvitalya.droiderhandbook.features.sections.ui.list.RealQuestionListComponent

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




/*
fun ComponentFactory.createPokemonDetailsComponent(
    componentContext: ComponentContext,
    pokemonId: PokemonId
): PokemonDetailsComponent {
    val pokemonReplica = get<PokemonRepository>().pokemonByIdReplica.withKey(pokemonId)
    return RealPokemonDetailsComponent(componentContext, pokemonReplica, get())
}
 */