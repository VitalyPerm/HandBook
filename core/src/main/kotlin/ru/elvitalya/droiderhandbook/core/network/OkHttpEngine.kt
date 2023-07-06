package ru.elvitalya.droiderhandbook.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import ru.elvitalya.droiderhandbook.core.debug_tools.DebugTools

fun createOkHttpEngine(debugTools: DebugTools): HttpClientEngine {
    return OkHttp.create {
        debugTools.interceptors.forEach { addInterceptor(it) }
    }
}
