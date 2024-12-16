package productDetailPage.domain.useCases

import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.ExtendedProductInfo
import productDetailPage.domain.models.Product

class GetExtendedProductInfoByIdImpl(private val repo: ProductRepository) : GetExtendedProductInfoById {
    override suspend fun invoke(id: String): ExtendedProductInfo {
        return repo.getProductExtendedInfo(id)
    }
}