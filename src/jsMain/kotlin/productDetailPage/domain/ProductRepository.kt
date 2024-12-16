package productDetailPage.domain

import productDetailPage.domain.models.ExtendedProductInfo
import productDetailPage.domain.models.Product

interface ProductRepository {
    suspend fun getProductById(id: String): Product
    suspend fun getProductExtendedInfo(id: String): ExtendedProductInfo
}