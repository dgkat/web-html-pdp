package productDetailPage.domain.useCases

import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.Product

class GetProductByIdUseCaseImpl(private val repo: ProductRepository) : GetProductByIdUseCase {
    override suspend fun invoke(id: String): Product {
        return repo.getProductById(id)
    }
}