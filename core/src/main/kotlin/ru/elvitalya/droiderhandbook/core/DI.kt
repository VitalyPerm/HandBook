package ru.elvitalya.droiderhandbook.core

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.network.AndroidNetworkConnectivityProvider
import me.aartikov.replica.network.NetworkConnectivityProvider
import org.koin.core.component.get
import org.koin.dsl.module
import ru.elvitalya.droiderhandbook.core.activity.ActivityProvider
import ru.elvitalya.droiderhandbook.core.debug_tools.DebugTools
import ru.elvitalya.droiderhandbook.core.debug_tools.RealDebugTools
import ru.elvitalya.droiderhandbook.core.error_handling.ErrorHandler
import ru.elvitalya.droiderhandbook.core.message.data.MessageService
import ru.elvitalya.droiderhandbook.core.message.data.MessageServiceImpl
import ru.elvitalya.droiderhandbook.core.message.ui.MessageComponent
import ru.elvitalya.droiderhandbook.core.message.ui.RealMessageComponent
import ru.elvitalya.droiderhandbook.core.network.ErrorCollector
import ru.elvitalya.droiderhandbook.core.network.NetworkApiFactory
import ru.elvitalya.droiderhandbook.core.network.createOkHttpEngine
import ru.elvitalya.droiderhandbook.core.permissions.PermissionService

fun coreModule(backendUrl: String) = module {
    single { ActivityProvider() }
    single<NetworkConnectivityProvider> { AndroidNetworkConnectivityProvider(get()) }
    single { ReplicaClient(get()) }
    single<MessageService> { MessageServiceImpl() }
    single { ErrorHandler(get()) }
    single<DebugTools> {
        RealDebugTools(
            get(),
            get()
        )
    }
    single { createOkHttpEngine(get()) }
    single {

        NetworkApiFactory(
            //loggingEnabled = BuildConfig.DEBUG,
            loggingEnabled = true,
            backendUrl = backendUrl,
            httpClientEngine = get(),
            errorCollector = get()
        )
    }
    single {
        val debugTools = get<DebugTools>()
        ErrorCollector { debugTools.collectNetworkError(it) }
    }
    single(createdAtStart = true) { PermissionService(get(), get()) }

}

fun ComponentFactory.createMessageComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(componentContext, get())
}
