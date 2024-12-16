package core.data

interface ProductDao {
    suspend fun addProduct( product: LocalProduct)

    suspend fun deleteProduct(productId: String)

    suspend fun getProductById( productId: String): LocalProduct?
}