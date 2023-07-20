package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain.Question


@Serializable
class QuestionResponseWrapper(
    @SerialName("items") val questions: List<QuestionResponse>
)

@Serializable
class QuestionResponse(
    @SerialName("number") val number: Int,
    @SerialName("title") val title: String,
    @SerialName("text") val text: String,
    @SerialName("picUrl") val picUrl: String,
)

fun QuestionResponse.toDomain(): Question = Question(number, title, text, picUrl)