package productDetailPage.domain.useCases

import kotlinx.coroutines.flow.Flow
import productDetailPage.domain.models.Product

interface ObserveProduct {
    operator fun invoke(id: String): Flow<Product>
}