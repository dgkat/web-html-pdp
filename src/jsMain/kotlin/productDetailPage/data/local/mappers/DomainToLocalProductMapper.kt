package productDetailPage.data.local.mappers

import core.data.LocalProduct
import js.core.jso
import productDetailPage.domain.models.Product

class DomainToLocalProductMapper {
    fun map(domainProduct: Product): LocalProduct {
        val product = jso<LocalProduct> {
            id = domainProduct.id
            name = domainProduct.name
            type = domainProduct.type
            imageUrl = domainProduct.imageUrl
            price = domainProduct.price
            description = domainProduct.description
        }
        return product
    }
}
