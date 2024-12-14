package productDetailPage.domain.useCases

import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.ExtendedProductInfo

class GetExtendedProductInfoByIdImpl(private val repo: ProductRepository) : GetExtendedProductInfoById {
    override suspend fun invoke(id: String): ExtendedProductInfo {
        return repo.getProductExtendedInfoFromRemote(id)
    }
}