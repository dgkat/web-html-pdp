package core.data

import core.util.TimeProvider
import js.core.jso
import kotlinx.datetime.Clock
import productDetailPage.domain.models.Product


class DBTest(private val timeProvider: TimeProvider) {
    suspend fun testDatabaseOperations() {
        println("DatabaseTest  testDb fun entry")
        val database = openProductsDatabase()


        val product = jso<LocalProduct> {
            id = "123"
            name = "Test Product"
            type = "Type A"
            imageUrl = "http://example.com/image.png"
            price = 19.99f
            description = "A test product"
            timestamp = timeProvider.getCurrentTimeInMilliseconds().toDouble()
        }

        addProduct(database, product)
        val fetchedProduct = getProductById(database, "123")
        val mappedProduct = fetchedProduct?.let {
            Product(
                id = it.id,
                name = it.name,
                type = it.type,
                imageUrl = it.imageUrl,
                price = it.price,
                description = it.description,
                timestamp = it.timestamp.toLong()
            )
        }
        println("DatabaseTest  fetchedProduct -> $mappedProduct")

        removeProduct(database, "123")
        val deletedProduct = getProductById(database, "123")
        println("DatabaseTest  deletedProduct -> $deletedProduct")
    }
}
