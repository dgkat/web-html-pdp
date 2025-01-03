package productDetailPage.domain.useCases

import productDetailPage.domain.models.Product

interface AddProductToCart {
    suspend operator fun invoke(product: Product)
}