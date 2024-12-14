package productDetailPage.domain.useCases

import productDetailPage.domain.ProductRepository
import productDetailPage.domain.models.Product

class GetProductByIdUseCaseImpl(private val repo: ProductRepository) : GetProductByIdUseCase {
    override suspend fun invoke(id: String): Product {
        val localProduct = getLocalProductById(id)
        println("local Product pre $localProduct")
        if (localProduct != null){
            println("local Product in $localProduct")
            return localProduct
        }

        return getRemoteProductById(id)
    }

    private suspend fun getLocalProductById(id: String):Product?{
        return repo.getProductFromDb(id)
    }

    private suspend fun getRemoteProductById(id: String) :Product{
        return repo.getProductFromRemote(id)
    }
}