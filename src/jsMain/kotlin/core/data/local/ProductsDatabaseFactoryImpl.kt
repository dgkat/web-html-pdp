package core.data.local

import web.events.EventHandler
import web.events.EventType
import web.events.addEventListener
import web.idb.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ProductsDatabaseFactoryImpl : DatabaseFactory {
    private var database: IDBDatabase? = null

    override suspend fun getDatabase(): IDBDatabase {
        if (database == null) {
            database = createDatabase()
        }
        return database!!
    }

    private suspend fun createDatabase(): IDBDatabase {
        val idbFactory = indexedDB ?: error("IndexedDB is not supported by this browser")
        val request = idbFactory.open("ecommerceDB", 1.0)

        request.onupgradeneeded = EventHandler { event ->
            val db = (event.target as IDBOpenDBRequest).result
            if (!db.objectStoreNames.contains("products")) {
                /*db.createObjectStore("products", jsObject<dynamic> {
                    this["keyPath"] = "id"
                })*/

                val objectStoreParams = js("new Object()")
                objectStoreParams.keyPath = "id"

                db.createObjectStore("products", options = objectStoreParams)
            }
        }

        // Await the database opening
        return suspendCoroutine { continuation ->

            request.onsuccess = EventHandler { event ->
                val db = (event.target as IDBOpenDBRequest).result
                continuation.resume(db)
            }

            request.onerror = EventHandler { event ->
                val error = (event.target as IDBOpenDBRequest).error
                continuation.resumeWithException(Exception("Failed to open database: $error"))
            }
        }
    }
}

inline fun <T : Any> jsObject(builder: T.() -> Unit): T =
    (js("{}") as T).apply(builder)

suspend fun testDatabase() {
    val dbFactory = ProductsDatabaseFactoryImpl()
    val db = dbFactory.getDatabase()

    // Adding a test product
    val transaction = db.transaction("products", IDBTransactionMode.readwrite)
    val store = transaction.objectStore("products")

    val testProduct = jsObject<dynamic> {
        this["id"] = "1"
        this["name"] = "Test Product"
        this["type"] = "Test Type"
        this["imageUrl"] = "https://via.placeholder.com/150"
        this["price"] = 9.99
        this["description"] = "This is a test product."
    }

    store.put(testProduct)

    transaction.addEventListener(EventType("complete"), {
        console.log("Transaction complete: Product added to database.")
    })

    transaction.addEventListener(EventType("error"), {
        console.error("Transaction failed: ${transaction.error}")
    })

    // Retrieving the test product
    val getRequest = store.get(IDBValidKey(1))
    getRequest.addEventListener(EventType("complete"),{
        console.log("Product retrieved : ${getRequest.result}.")
    })

    getRequest.addEventListener(EventType("error"), {
        console.error("Failed to retrieve product: ${getRequest.error}")
    })
}