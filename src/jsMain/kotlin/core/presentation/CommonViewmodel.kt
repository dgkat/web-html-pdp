package core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class CommonViewModel {

    // Coroutine scope tied to the lifecycle of the ViewModel
    private val viewModelJob = SupervisorJob()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * Called to clean up resources. Cancel the coroutine scope to prevent memory leaks.
     */
    open fun onCleared() {
        viewModelJob.cancel()
    }
}