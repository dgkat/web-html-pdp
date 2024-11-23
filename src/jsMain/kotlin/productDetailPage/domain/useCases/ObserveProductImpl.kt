package productDetailPage.domain.useCases

import kotlinx.coroutines.flow.Flow
import productDetailPage.domain.models.Product

class ObserveProductImpl(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getExtendedProductInfoById: GetExtendedProductInfoById
) : ObserveProduct {
    //TODO rename product(and get etc) to ProductBasic?
    override fun invoke(id: String): Flow<Product> {
        TODO("Not yet implemented")
    }
}