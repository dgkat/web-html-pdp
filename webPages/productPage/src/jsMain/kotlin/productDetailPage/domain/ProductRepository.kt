package productDetailPage.domain

import productDetailPage.domain.models.ExtendedProductInfo
import productDetailPage.domain.models.Product

interface ProductRepository {
    suspend fun getProductFromRemote(id: String): Product
    suspend fun getProductExtendedInfoFromRemote(id: String): ExtendedProductInfo

    suspend fun upsertToDb(product: Product)

    suspend fun getProductFromDb(id: String): Product?

    suspend fun deleteFromDb(id: String)
}