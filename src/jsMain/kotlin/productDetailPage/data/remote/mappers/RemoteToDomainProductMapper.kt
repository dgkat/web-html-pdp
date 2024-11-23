package productDetailPage.data.remote.mappers

import productDetailPage.data.remote.models.RemoteProduct
import productDetailPage.domain.models.Product

class RemoteToDomainProductMapper {
    fun map(remoteProduct: RemoteProduct): Product {
        return Product(
            name = "name",
            id = "1",
            type = "type",
            imageUrl = "https://i.postimg.cc/LsXBmP7n/test-laptop-1.jpg",
            price = 19.99f,
            description = remoteProduct.fact
        )
    }
}