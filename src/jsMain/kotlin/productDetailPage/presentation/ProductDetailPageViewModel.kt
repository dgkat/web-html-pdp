package productDetailPage.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import productDetailPage.domain.useCases.AddProductToCart
import productDetailPage.domain.useCases.ObserveProduct
import productDetailPage.domain.useCases.RemoveProductFromCart
import productDetailPage.presentation.mappers.DomainToUiProductMapper
import productDetailPage.presentation.mappers.UiToDomainProductMapper
import productDetailPage.presentation.models.UiProduct

class ProductDetailPageViewModel(
    private val domainToUiProductMapper: DomainToUiProductMapper,
    private val observeProduct: ObserveProduct,
    private val addProductToCart: AddProductToCart,
    private val removeProductFromCart: RemoveProductFromCart,
    private val uiToDomainProductMapper: UiToDomainProductMapper
) {
    private val _state = MutableStateFlow(ProductDetailPageState())
    val state: StateFlow<ProductDetailPageState> = _state

    init {
        observeProduct()
    }

    private fun observeProduct() {
        CoroutineScope(Dispatchers.Main).launch {
            observeProduct("1").collect { product ->
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

    private fun addProductToCart() {
        CoroutineScope(Dispatchers.Main).launch {
            state.value.product?.let {
                addProductToCart(product = uiToDomainProductMapper.map(it))
            }
        }
    }

    private fun removeProductFromCart() {
        CoroutineScope(Dispatchers.Main).launch {
            state.value.product?.let { uiProduct: UiProduct ->
                removeProductFromCart(uiProduct.id)
            }
        }
    }

    fun onEvent(event: ProductDetailPageEvent) {
        when (event) {
            is ProductDetailPageEvent.AddToCart -> _state.update {
                addProductToCart()
                it.copy(
                    isInCart = true
                )
            }

            is ProductDetailPageEvent.RemoveFromCart -> _state.update {
                removeProductFromCart()
                it.copy(
                    isInCart = false
                )
            }
        }
    }
}