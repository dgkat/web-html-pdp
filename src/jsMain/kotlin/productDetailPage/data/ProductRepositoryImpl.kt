package productDetailPage.data

import productDetailPage.data.remote.service.ProductService
import productDetailPage.data.remote.mappers.RemoteToDomainProductMapper
import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.Product

class ProductRepositoryImpl(
    private val remoteClient: ProductService,
    private val remoteToDomainProductMapper: RemoteToDomainProductMapper
) : ProductRepository {
    override suspend fun getProductById(id: String): Product {
        val remoteProduct = remoteClient.getProductById()
        return remoteToDomainProductMapper.map(remoteProduct)
    }
}