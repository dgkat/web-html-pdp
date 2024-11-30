package productDetailPage.data

import productDetailPage.data.remote.mappers.RemoteToDomainExtendedProductInfoMapper
import productDetailPage.data.remote.mappers.RemoteToDomainProductMapper
import productDetailPage.data.remote.service.ProductService
import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.ExtendedProductInfo
import productDetailPage.domain.models.Product

class ProductRepositoryImpl(
    private val productService: ProductService,
    private val remoteToDomainProductMapper: RemoteToDomainProductMapper,
    private val remoteToDomainExtendedProductInfoMapper: RemoteToDomainExtendedProductInfoMapper
) : ProductRepository {
    override suspend fun getProductById(id: String): Product {
        val remoteProduct = productService.getProductById()
        return remoteToDomainProductMapper.map(remoteProduct)
    }

    override suspend fun getProductExtendedInfo(id: String): ExtendedProductInfo {
        val remoteExtendedProductInfo = productService.getExtendedProductInfo()
        return remoteToDomainExtendedProductInfoMapper.map(remoteExtendedProductInfo)
    }
}