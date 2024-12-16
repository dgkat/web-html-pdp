package productDetailPage.data.local.mappers

import core.data.LocalProduct
import productDetailPage.domain.models.Product

class LocalToDomainProductMapper {
    fun map(localProduct: LocalProduct): Product {
        return Product(
            name = localProduct.name,
            id = localProduct.id,
            type = localProduct.type,
            imageUrl = localProduct.imageUrl,
            price = localProduct.price,
            description = localProduct.description,
            timestamp = localProduct.timestamp.toLong()
        )
    }
}