package productDetailPage.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import productDetailPage.domain.useCases.GetProductByIdUseCase
import productDetailPage.domain.useCases.ObserveProduct
import productDetailPage.presentation.mappers.DomainToUiProductMapper

class ProductDetailPageViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val domainToUiProductMapper: DomainToUiProductMapper,
    private val observeProduct: ObserveProduct
) {
    private val _state = MutableStateFlow(ProductDetailPageState())
    val state: StateFlow<ProductDetailPageState> = _state

    init {
        observeProduct()
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

    private fun observeProduct() {
        CoroutineScope(Dispatchers.Main).launch {
            observeProduct("flowId").collect { product ->
                val uiProduct = domainToUiProductMapper.map(product)
                _state.update {
                    it.copy(
                        product = uiProduct,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun addProductToCart(){
        CoroutineScope(Dispatchers.Main).launch {

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