package core.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import web.idb.IDBDatabase
import web.idb.IDBFactory

class testDB {
}

suspend fun openDatabase(

): IDBDatabase= withContext(Dispatchers.Unconfined) {
    // Use js interop to access indexedDB API via self (global object) for compatibility
    val indexedDB: IDBFactory? = js("self.indexedDB || self.webkitIndexedDB") as? IDBFactory

    // Check if IndexedDB is available in the current environment
    val factory = checkNotNull(indexedDB) { "Your browser doesn't support IndexedDB." }

    // Open the database
    val request = factory.open("dbname", 1.0)

    // Listen for the different events related to the opening of the database
    /*val versionChangeEvent = request.onNextEvent("success", "upgradeneeded", "error", "blocked") { event ->
        when (event.type) {
            "upgradeneeded" -> event as IDBVersionChangeEvent
            "error" -> throw ErrorEventException(event)
            "blocked" -> throw OpenBlockedException(name, event)
            else -> null
        }
    }*/

    val ev = request.onupgradeneeded
    println("database event $ev")

    // Return the opened database
    /*Database(request.result).also { database ->
        if (versionChangeEvent != null) {
            val transaction = VersionChangeTransaction(checkNotNull(request.transaction))
            transaction.initialize(database, versionChangeEvent.oldVersion, versionChangeEvent.newVersion)
            transaction.awaitCompletion() // Wait for the transaction to complete
        }
    }*/

    return@withContext request.result
}