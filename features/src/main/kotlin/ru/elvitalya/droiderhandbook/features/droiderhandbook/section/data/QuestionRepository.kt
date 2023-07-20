package ru.elvitalya.droiderhandbook.features.droiderhandbook.section.data

import QuestionTypeId
import me.aartikov.replica.keyed.KeyedReplica
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.domain.Question

interface QuestionRepository {
    val questionsByTypeReplica: KeyedReplica<QuestionTypeId, List<Question>>
}