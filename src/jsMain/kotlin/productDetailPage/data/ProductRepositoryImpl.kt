package productDetailPage.data

import core.data.ProductDao
import productDetailPage.data.local.mappers.DomainToLocalProductMapper
import productDetailPage.data.local.mappers.LocalToDomainProductMapper
import productDetailPage.data.remote.mappers.RemoteToDomainExtendedProductInfoMapper
import productDetailPage.data.remote.mappers.RemoteToDomainProductMapper
import productDetailPage.data.remote.service.ProductService
import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.ExtendedProductInfo
import productDetailPage.domain.models.Product

class ProductRepositoryImpl(
    private val productService: ProductService,
    private val productDao: ProductDao,
    private val remoteToDomainProductMapper: RemoteToDomainProductMapper,
    private val remoteToDomainExtendedProductInfoMapper: RemoteToDomainExtendedProductInfoMapper,
    private val localToDomainProductMapper: LocalToDomainProductMapper,
    private val domainToLocalProductMapper: DomainToLocalProductMapper
) : ProductRepository {
    override suspend fun getProductFromRemote(id: String): Product {
        val remoteProduct = productService.getProductById()
        return remoteToDomainProductMapper.map(remoteProduct)
    }

    override suspend fun getProductExtendedInfoFromRemote(id: String): ExtendedProductInfo {
        val remoteExtendedProductInfo = productService.getExtendedProductInfo()
        return remoteToDomainExtendedProductInfoMapper.map(remoteExtendedProductInfo)
    }

    override suspend fun upsertToDb(product: Product) {
        productDao.addProduct(
            domainToLocalProductMapper.map(product)
        )
    }

    override suspend fun getProductFromDb(id: String): Product? {
        val product = productDao.getProductById(id)
        return product?.let { localToDomainProductMapper.map(it) }
    }

    override suspend fun deleteFromDb(id: String) {
        return productDao.deleteProduct(id)
    }
}