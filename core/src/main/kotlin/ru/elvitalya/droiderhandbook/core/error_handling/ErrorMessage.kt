package ru.elvitalya.droiderhandbook.core.error_handling

import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import ru.elvitalya.droiderhandbook.core.BuildConfig
import ru.elvitalya.droiderhandbook.core.R
import ru.elvitalya.droiderhandbook.core.utils.Resource
import ru.elvitalya.droiderhandbook.core.utils.ResourceFormatted

/**
 * Returns human readable messages for exceptions.
 */
val Exception.errorMessage: StringDesc
    get() = when (this) {

        is ServerException -> message?.let { StringDesc.Raw(it) }
            ?: StringDesc.Resource(R.string.error_invalid_response)

        is DeserializationException -> StringDesc.Resource(R.string.error_invalid_response)

        is NoServerResponseException -> StringDesc.Resource(R.string.error_no_server_response)

        is NoInternetException -> StringDesc.Resource(R.string.error_no_internet_connection)

        is ExternalAppNotFoundException -> StringDesc.Resource(R.string.error_matching_application_not_found)

        else -> {
            val description = this.message
            if (description != null && BuildConfig.DEBUG) {
                StringDesc.ResourceFormatted(
                    R.string.error_unexpected_with_description,
                    description
                )
            } else {
                StringDesc.Resource(R.string.error_unexpected)
            }
        }
    }
