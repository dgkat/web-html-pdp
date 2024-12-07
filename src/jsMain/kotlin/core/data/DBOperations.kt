package core.data

/*
suspend fun addProduct(database: Database, product: Product) {
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

suspend fun getProductById(database: Database, productId: String): Product? {
    return database.transaction("products") {
        objectStore("products").get(Key(productId)) as Product?
    }
}

suspend fun getProductByName(database: Database, productName: String): Product? {
    return database.transaction("products") {
        objectStore("products").index("name").get(Key(productName)) as Product?
    }
}

*/
/*suspend fun getProductsByFavorite(database: Database, isFavorite: Boolean): List<Product> {
    return database.transaction("products") {
        objectStore("products").openCursor().map { it.value as Product }.filter { it.isFavorite == isFavorite }.toList()
    }
}*//*


suspend fun getAllProducts(database: Database): List<Product> {
    return database.transaction("products") {
        objectStore("products").getAll().map { it as Product }
    }
}

suspend fun removeProductsBefore(database: Database, timestamp: Double) {
    database.writeTransaction("products") {
        objectStore("products").openCursor().collect { cursor ->
            val product = cursor.value as Product
            if (product.timestamp < timestamp) {
                cursor.delete()
            }
        }
    }
}*/
