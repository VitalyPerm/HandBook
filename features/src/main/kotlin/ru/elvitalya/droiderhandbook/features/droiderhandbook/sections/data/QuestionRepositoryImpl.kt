package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.data

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.ReplicaSettings
import ru.elvitalya.droiderhandbook.core.network.DroiderHandBookApiFactory
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.data.dto.toDomain
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.QuestionTypeId
import kotlin.time.Duration.Companion.seconds

class QuestionRepositoryImpl(
    replicaClient: ReplicaClient,
    droiderHandBookApiFactory: DroiderHandBookApiFactory
) : QuestionRepository {

    val api = droiderHandBookApiFactory.ktorfit.create<QuestionsApi>()

    override val questionsByTypeReplica: KeyedReplica<QuestionTypeId, List<Question>> =
        replicaClient.createKeyedReplica(
            name = "questionsByType",
            childName = { typeId -> "typeId = ${typeId.value}" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 10.seconds,
                    clearTime = 60.seconds
                )
            }
        ) { questionTypeId ->
            when (questionTypeId.value) {
                "1" -> api.getJavaQuestions().questions.map { it.toDomain() }
                "2" -> api.getKotlinQuestions().questions.map { it.toDomain() }
                "3" -> api.getAndroidQuestions().questions.map { it.toDomain() }
                else -> api.getCoroutineQuestions().questions.map { it.toDomain() }
            }
        }
}