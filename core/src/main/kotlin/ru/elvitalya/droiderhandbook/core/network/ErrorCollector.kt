package ru.elvitalya.droiderhandbook.core.network

import ru.elvitalya.droiderhandbook.core.error_handling.ApplicationException

fun interface ErrorCollector {
    fun collectNetworkError(exception: ApplicationException)
}
