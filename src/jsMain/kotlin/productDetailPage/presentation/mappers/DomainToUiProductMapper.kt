package productDetailPage.presentation.mappers

import productDetailPage.domain.models.Product
import productDetailPage.presentation.models.UiProduct

class DomainToUiProductMapper {
    fun map(product: Product): UiProduct {
        return UiProduct(
            name = product.name,
            id = product.id,
            type = product.type,
            imageUrl = product.imageUrl,
            price = product.price,
            description = product.description
        )
    }
}