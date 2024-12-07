package core.data

import com.juul.indexeddb.Database
import com.juul.indexeddb.KeyPath
import com.juul.indexeddb.openDatabase

suspend fun openProductsDatabase(): Database {
    return openDatabase("ecommerceDB", 1) { database, oldVersion, _ ->
        if (oldVersion < 1) {
            val store = database.createObjectStore("products", KeyPath("id"))
            store.createIndex("name", KeyPath("name"), unique = false)
            store.createIndex("type", KeyPath("type"), unique = false)
            store.createIndex("timestamp", KeyPath("timestamp"), unique = false)
        }
    }
}
