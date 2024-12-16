package productDetailPage.domain.useCases

import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.Product

class AddProductToCartImpl(private val productRepository: ProductRepository) : AddProductToCart {
    override suspend fun invoke(product:Product) {
        productRepository.upsertToDb(product)
    }
}