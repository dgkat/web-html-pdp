package productDetailPage.presentation

import ProductState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import productDetailPage.domain.useCases.GetProductByIdUseCase
import productDetailPage.presentation.mappers.DomainToUiProductMapper

class ProductDetailPageViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val domainToUiProductMapper: DomainToUiProductMapper
) {
    private val _state = MutableStateFlow<ProductState>(ProductState.Loading)
    val state: StateFlow<ProductState> = _state

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        CoroutineScope(Dispatchers.Main).launch {
            _state.value = try {
                val product = domainToUiProductMapper.map(getProductByIdUseCase(id = "-"))
                ProductState.Success(product)
            } catch (e: Exception) {
                ProductState.Error("Failed to load product: ${e.message}")
            }
        }
    }
}