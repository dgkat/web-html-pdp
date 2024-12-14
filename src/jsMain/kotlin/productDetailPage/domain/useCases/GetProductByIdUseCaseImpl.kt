package productDetailPage.domain.useCases

import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.Product

class GetProductByIdUseCaseImpl(private val repo: ProductRepository) : GetProductByIdUseCase {
    override suspend fun invoke(id: String): Product {
        val localProduct = getLocalProductById(id)

        if (localProduct != null){
            return localProduct
        }

        return geRemoteProductById(id)
    }

    private suspend fun getLocalProductById(id: String):Product?{
        return repo.getProductFromDb(id)
    }

    private suspend fun geRemoteProductById(id: String) :Product{
        return repo.getProductFromRemote(id)
    }
}