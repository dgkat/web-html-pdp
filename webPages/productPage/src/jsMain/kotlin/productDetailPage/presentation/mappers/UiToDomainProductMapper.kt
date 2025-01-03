package productDetailPage.presentation.mappers

import productDetailPage.domain.models.Product
import productDetailPage.presentation.models.UiProduct

class UiToDomainProductMapper {
    fun map(uiProduct: UiProduct) : Product{
        return Product(
            name = uiProduct.name,
            id = uiProduct.id,
            type = uiProduct.type,
            imageUrl = uiProduct.imageUrl,
            price = uiProduct.price,
            description = uiProduct.description
        )
    }
}