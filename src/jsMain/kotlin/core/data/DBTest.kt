package core.data

import core.util.TimeProvider
import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.Product


class DBTest(private val timeProvider: TimeProvider,private val repository: ProductRepository) {
    suspend fun testDatabaseOperations() {
        println("DatabaseTest  testDb fun entry")


        val product = Product (
            id = "123",
            name = "Test Product",
            type = "Type A",
            imageUrl = "http://example.com/image.png",
            price = 19.99f,
            description = "A test product",
            timestamp = timeProvider.getCurrentTimeInMilliseconds()
        )

        repository.upsertToDb( product)
        val fetchedProduct = repository.getProductFromDb( "123")
        /*val mappedProduct = fetchedProduct?.let {
            Product(
                id = it.id,
                name = it.name,
                type = it.type,
                imageUrl = it.imageUrl,
                price = it.price,
                description = it.description,
                timestamp = it.timestamp.toLong()
            )
        }*/
        println("DatabaseTest  fetchedProduct -> $fetchedProduct")

        repository.deleteFromDb( "123")
        val deletedProduct = repository.getProductFromDb("123")
        println("DatabaseTest  deletedProduct -> $deletedProduct")
    }
}
