package productDetailPage.data

import productDetailPage.data.remote.client.ProductClient
import productDetailPage.data.remote.mappers.RemoteToDomainProductMapper
import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.Product

class ProductRepositoryImpl(
    private val remoteClient: ProductClient,
    private val remoteToDomainProductMapper: RemoteToDomainProductMapper
) : ProductRepository {
    override suspend fun getProductById(id: String): Product {
        val remoteProduct = remoteClient.getProductById()
        return remoteToDomainProductMapper.map(remoteProduct)
    }
}