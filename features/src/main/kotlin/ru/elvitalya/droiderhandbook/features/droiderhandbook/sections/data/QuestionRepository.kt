package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.data

import me.aartikov.replica.keyed.KeyedReplica
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.QuestionTypeId

interface QuestionRepository {
    val questionsByTypeReplica: KeyedReplica<QuestionTypeId, List<Question>>
}