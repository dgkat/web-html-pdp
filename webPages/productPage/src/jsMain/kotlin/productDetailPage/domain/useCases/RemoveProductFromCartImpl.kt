package productDetailPage.domain.useCases

import productDetailPage.domain.ProductRepository

class RemoveProductFromCartImpl(private val productRepository: ProductRepository) : RemoveProductFromCart {
    override suspend fun invoke(id: String) {
        productRepository.deleteFromDb(id = id)
    }
}