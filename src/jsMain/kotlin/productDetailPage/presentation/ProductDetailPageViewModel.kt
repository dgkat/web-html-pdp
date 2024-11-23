package productDetailPage.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import productDetailPage.domain.useCases.GetProductByIdUseCase
import productDetailPage.presentation.mappers.DomainToUiProductMapper

class ProductDetailPageViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val domainToUiProductMapper: DomainToUiProductMapper
) {
    private val _state = MutableStateFlow(ProductDetailPageState())
    val state: StateFlow<ProductDetailPageState> = _state

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val product = domainToUiProductMapper.map(getProductByIdUseCase("-"))
                _state.update {
                    it.copy(
                        product = product,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        product = null,
                        isLoading = false,
                        error = "Error"
                    )
                }
            }
        }
    }

    fun onEvent(event: ProductDetailPageEvent) {
        when (event) {
            is ProductDetailPageEvent.AddToCart -> _state.update {
                it.copy(
                    isInCart = true
                )
            }

            is ProductDetailPageEvent.RemoveFromCart -> _state.update {
                it.copy(
                    isInCart = false
                )
            }
        }
    }
}