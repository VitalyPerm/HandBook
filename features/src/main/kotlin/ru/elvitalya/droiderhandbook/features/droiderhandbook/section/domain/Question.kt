package ru.elvitalya.droiderhandbook.features.droiderhandbook.section.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val number: Int,
    val title: String,
    val text: String,
    val picUrl: String
) : Parcelable