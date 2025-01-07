package productDetailPage.domain.useCases

import productDetailPage.domain.models.ExtendedProductInfo

interface GetExtendedProductInfoById {
    suspend operator fun invoke(id: String): ExtendedProductInfo
}