package productDetailPage.domain

import productDetailPage.domain.models.Product

interface ProductRepository {
    suspend fun getProductById(id: String): Product
}