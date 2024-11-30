package core.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import web.idb.*

/**
 * Simplified version of openDatabase function for initializing IndexedDB.
 */
suspend fun openDatabase(
    name: String,
    //version: Int,
   // initialize: suspend VersionChangeTransaction.(Database) -> Unit
): Database = withContext(Dispatchers.Unconfined) {
    val indexedDB: IDBFactory? = js("self.indexedDB || self.webkitIndexedDB") as? IDBFactory
    val factory = checkNotNull(indexedDB) { "Your browser doesn't support IndexedDB." }
    val request = factory.open(name, 1.0)

    /*var upgraded = false
    request.onupgradeneeded = { event ->
        upgraded = true
        val db = request.result
        val transaction = VersionChangeTransaction(checkNotNull(request.transaction))
        initialize(transaction, Database(db))
    }

    request.onerror = { event ->
        console.error("Error opening database: ${(event.target as IDBOpenDBRequest).error?.message}")
    }

    request.onblocked = {
        console.warn("Database is blocked from upgrading.")
    }

    request.onsuccess = {
        console.log("Database opened successfully!")
    }*/

    // Return the database instance
    Database(request.result)
}

/**
 * Simplified wrapper for IndexedDB database operations.
 */
class Database(private val db: IDBDatabase) {
    fun createStore(name: String, options: dynamic = js("{}")) {
        if (!db.objectStoreNames.contains(name)) {
            db.createObjectStore(name, options)
        }
    }

    fun close() {
        db.close()
    }
}

/**
 * Wrapper for handling version change transactions.
 */
/*class VersionChangeTransaction(private val transaction: IDBTransaction) {
    suspend fun awaitCompletion() = transaction.awaitComplete()
}*/

/**
 * Extension function for awaiting IndexedDB request results.
 */
/*suspend fun <T> IDBRequest<T>.awaitResult(): T = withContext(Dispatchers.Unconfined) {
    suspendCancellableCoroutine { continuation ->
        onsuccess = { continuation.resumeWith(Result.success(result)) }
        onerror = { continuation.resumeWith(Result.failure(error ?: RuntimeException("Unknown error"))) }
    }
}*/

/**
 * Extension function for awaiting transaction completion.
 */
/*
suspend fun IDBTransaction.awaitComplete() = withContext(Dispatchers.Unconfined) {
    suspendCancellableCoroutine<Unit> { continuation ->
        oncomplete = { continuation.resumeWith(Result.success(Unit)) }
        onerror = { continuation.resumeWith(Result.failure(error ?: RuntimeException("Transaction error"))) }
    }
}*/
