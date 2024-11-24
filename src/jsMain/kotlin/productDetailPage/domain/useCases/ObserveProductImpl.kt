package productDetailPage.domain.useCases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import productDetailPage.domain.models.Product

class ObserveProductImpl(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getExtendedProductInfoById: GetExtendedProductInfoById
) : ObserveProduct {
    //TODO rename product(and get etc) to ProductBasic?
    override fun invoke(id: String): Flow<Product> = flow {
        val product = getProductByIdUseCase(id)

        emit(product)

        val extendedProductInfo = getExtendedProductInfoById(id)

        val productWithExtendedProductInfo = product.copy(extendedProductInfo = extendedProductInfo)

        emit(productWithExtendedProductInfo)
    }
}