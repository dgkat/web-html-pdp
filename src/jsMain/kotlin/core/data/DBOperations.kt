package core.data

import com.juul.indexeddb.Database
import com.juul.indexeddb.Key
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

suspend fun addProduct(database: Database, product: LocalProduct) {
    database.writeTransaction("products") {
        val store = objectStore("products")
        store.put(product)
    }
}

suspend fun removeProduct(database: Database, productId: String) {
    database.writeTransaction("products") {
        val store = objectStore("products")
        store.delete(Key(productId))
    }
}

suspend fun getProductById(database: Database, productId: String): LocalProduct? {
    return database.transaction("products") {
        objectStore("products").get(Key(productId)) as LocalProduct?
    }
}

suspend fun getProductByName(database: Database, productName: String): LocalProduct? {
    return database.transaction("products") {
        objectStore("products").index("name").get(Key(productName)) as LocalProduct?
    }
}

/*suspend fun getProductsByFavorite(database: Database, isFavorite: Boolean): List<LocalProduct> {
    return database.transaction("products") {
        objectStore("products").openCursor().map { it.value as LocalProduct }.filter { it.isFavorite == isFavorite }.toList()
    }
}*/


suspend fun getAllProducts(database: Database): List<LocalProduct> {
    return database.transaction("products") {
        objectStore("products").getAll().map { it as LocalProduct }
    }
}

suspend fun removeProductsBefore(database: Database, timestamp: Double) {
    database.writeTransaction("products") {
        objectStore("products").openCursor(autoContinue = true).collect { cursor ->
            val product = cursor.value as LocalProduct
            if (product.timestamp < timestamp) {
                cursor.delete()
            }
        }
    }
}
