package core.data

import com.juul.indexeddb.Key

class ProductDaoImpl(private val dataBaseFactory: ProductDataBaseFactory) : ProductDao {

    //TODO move the dao to the respective feature folder.Use the singleton DB from the injected factory.
    // Only use the necessary operations for each feature
    override suspend fun addProduct(product: LocalProduct) {
        val database = dataBaseFactory.getProductsDatabase()
        database.writeTransaction("products") {
            val store = objectStore("products")
            store.put(product)
        }
    }

    override suspend fun deleteProduct(productId: String) {
        val database = dataBaseFactory.getProductsDatabase()
        database.writeTransaction("products") {
            val store = objectStore("products")
            store.delete(Key(productId))
        }
    }

    override suspend fun getProductById(productId: String): LocalProduct? {
        val database = dataBaseFactory.getProductsDatabase()
        return database.transaction("products") {
            objectStore("products").get(Key(productId)) as LocalProduct?
        }
    }

    suspend fun getProductByName(productName: String): LocalProduct? {
        val database = dataBaseFactory.getProductsDatabase()
        return database.transaction("products") {
            objectStore("products").index("name").get(Key(productName)) as LocalProduct?
        }
    }

    /*suspend fun getProductsByFavorite( isFavorite: Boolean): List<LocalProduct> {
    return database.transaction("products") {
        objectStore("products").openCursor().map { it.value as LocalProduct }.filter { it.isFavorite == isFavorite }.toList()
    }
    }*/

    suspend fun getAllProducts(): List<LocalProduct> {
        val database = dataBaseFactory.getProductsDatabase()
        return database.transaction("products") {
            objectStore("products").getAll().map { it as LocalProduct }
        }
    }

    suspend fun removeProductsBefore(timestamp: Double) {
        val database = dataBaseFactory.getProductsDatabase()
        database.writeTransaction("products") {
            objectStore("products").openCursor(autoContinue = true).collect { cursor ->
                val product = cursor.value as LocalProduct
                if (product.timestamp < timestamp) {
                    cursor.delete()
                }
            }
        }
    }
}