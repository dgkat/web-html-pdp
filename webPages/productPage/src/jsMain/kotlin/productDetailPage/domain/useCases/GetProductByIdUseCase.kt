package productDetailPage.domain.useCases

import productDetailPage.domain.models.Product

interface GetProductByIdUseCase {
    suspend operator fun invoke(id: String): Product
}