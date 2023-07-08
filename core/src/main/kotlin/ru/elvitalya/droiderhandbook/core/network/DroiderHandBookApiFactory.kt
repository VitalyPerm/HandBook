package ru.elvitalya.droiderhandbook.core.network

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

class DroiderHandBookApiFactory(
    private val loggingEnabled: Boolean,
    private val droiderHandBookUrl: String,
    private val httpClientEngine: HttpClientEngine,
    private val errorCollector: ErrorCollector?
) {
    companion object {
        private const val CONNECT_TIMEOUT_MILLISECONDS = 30000L
        private const val READ_WRITE_TIMEOUT_MILLISECONDS = 60000L
    }

    private val json = createDefaultJson()
    private val httpClient = createHttpClient()


    val ktorfit = createKtorfit(httpClient)

    private fun createHttpClient(): HttpClient {
        return HttpClient(httpClientEngine) {
            install(Logging) {
                logger = createKtorLogger()
                level = if (loggingEnabled) LogLevel.ALL else LogLevel.NONE
            }

            install(ContentNegotiation) {
                json(json)
            }

            install(HttpTimeout) {
                connectTimeoutMillis = CONNECT_TIMEOUT_MILLISECONDS
                requestTimeoutMillis = READ_WRITE_TIMEOUT_MILLISECONDS
            }

            defaultRequest {
                url(droiderHandBookUrl)
            }

            setupErrorConverter(errorCollector)
        }
    }

    private fun createKtorfit(httpClient: HttpClient): Ktorfit {
        return Ktorfit.Builder()
            .baseUrl(droiderHandBookUrl)
            .httpClient(httpClient)
            .build()
    }
}