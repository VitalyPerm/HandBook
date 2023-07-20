package ru.elvitalya.droiderhandbook.features.droiderhandbook.section.data

import de.jensklingenberg.ktorfit.http.GET
import ru.elvitalya.droiderhandbook.features.droiderhandbook.section.data.dto.QuestionResponseWrapper


interface QuestionsApi {
    @GET("exec?action=getJava")
    suspend fun getJavaQuestions(): QuestionResponseWrapper

    @GET("exec?action=getKotlin")
    suspend fun getKotlinQuestions(): QuestionResponseWrapper

    @GET("exec?action=getAndroid")
    suspend fun getAndroidQuestions(): QuestionResponseWrapper

    @GET("exec?action=getCoroutine")
    suspend fun getCoroutineQuestions(): QuestionResponseWrapper
}