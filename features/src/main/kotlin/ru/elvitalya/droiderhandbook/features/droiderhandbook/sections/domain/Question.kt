package ru.elvitalya.droiderhandbook.features.droiderhandbook.sections.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val number: Int,
    val title: String,
    val text: String,
    val picUrl: String
) : Parcelable