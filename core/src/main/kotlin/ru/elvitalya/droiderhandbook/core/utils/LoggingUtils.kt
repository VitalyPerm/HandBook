package ru.elvitalya.droiderhandbook.core.utils

import co.touchlab.kermit.Logger

fun Logger.e(throwable: Throwable) = e(throwable) { throwable.message.orEmpty() }
