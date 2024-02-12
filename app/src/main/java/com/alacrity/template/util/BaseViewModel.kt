package com.alacrity.template.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alacrity.template.BaseEvent
import com.alacrity.template.EventHandler
import com.alacrity.template.view_states.BaseViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

abstract class BaseViewModel<T : BaseEvent, V: BaseViewState>(defaultViewState: V) : ViewModel(), EventHandler<T> {

    protected val _viewState: MutableStateFlow<V> = MutableStateFlow(defaultViewState)

    fun <T> launch(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        methodName: String? = null,
        logError: String? = null,
        logSuccess: String? = null,
        onSuccess: ((value: T) -> Unit)? = null,
        onFailure: ((exception: Throwable) -> Unit)? = null,
        block: suspend () -> T
    ) {
        viewModelScope.launch(dispatcher) {
            safeCall(
                methodName = methodName ?: block.javaClass.enclosingMethod?.name,
                successLogs = logSuccess,
                errorLogs = logError
            ) {
                block()
            }.fold(
                onSuccess = { withContext(Dispatchers.Main.immediate) { onSuccess?.invoke(it) } },
                onFailure = { withContext(Dispatchers.Main.immediate) { onFailure?.invoke(it) } }
            )
        }
    }

    private suspend fun <T> safeCall(
        methodName: String?,
        successLogs: String? = null,
        errorLogs: String? = null,
        call: suspend () -> T
    ): Result<T> {
        return try {
            val result = call.invoke()
            (successLogs ?: "[${getOwnerNameForLogs()}: $methodName]").let {
                Timber.tag(LOG_TAG).d("$it SUCCESS with result | $result |")
            }
            Result.success(result)
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            (errorLogs ?: "[${getOwnerNameForLogs()}: $methodName]").let {
                Timber.tag(LOG_TAG).d("$it FAILURE with | $e |")
            }
            Result.failure(e)
        }
    }


    open fun getOwnerNameForLogs(): String = this.javaClass.simpleName.replace("ViewModel", "VM")

    protected fun BaseViewState.logReduce(event: BaseEvent) {
        Timber.d("Reduce $this $event")
    }

    companion object {
        private const val LOG_TAG = "COROUTINE"
    }

}