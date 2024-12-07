package core.data

import js.core.jso
import productDetailPage.domain.models.Product

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
            description = it.description
        )
    }
    println("DatabaseTest  fetchedProduct -> $mappedProduct")

    removeProduct(database, "123")
    val deletedProduct = getProductById(database, "123")
    println("DatabaseTest  deletedProduct -> $deletedProduct")
}
