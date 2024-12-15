package productDetailPage.presentation

import core.presentation.CommonViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import productDetailPage.domain.useCases.*
import productDetailPage.presentation.mappers.DomainToUiProductMapper
import productDetailPage.presentation.mappers.UiToDomainProductMapper
import productDetailPage.presentation.models.UiProduct

class ProductDetailPageViewModel(
    private val addProductToCart: AddProductToCart,
    private val removeProductFromCart: RemoveProductFromCart,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getExtendedProductInfoById: GetExtendedProductInfoById,
    private val domainToUiProductMapper: DomainToUiProductMapper,
    private val uiToDomainProductMapper: UiToDomainProductMapper
) : CommonViewModel() {
    private val _state = MutableStateFlow(ProductDetailPageState())
    val state: StateFlow<ProductDetailPageState> = _state

    init {
        observeProduct()
    }

    private fun observeProduct() {
        viewModelScope.launch {
            val customId = "1"

            val product = getProductByIdUseCase(customId)

            val uiProduct = domainToUiProductMapper.map(product)

            _state.update {
                it.copy(
                    product = uiProduct,
                    isLoading = false
                )
            }

            val extendedProductInfo = getExtendedProductInfoById(customId)

            val productWithExtendedProductInfo = product.copy(extendedProductInfo = extendedProductInfo)

            val uiProductWithExtendedInfo = domainToUiProductMapper.map(productWithExtendedProductInfo)
            _state.update {
                it.copy(
                    product = uiProductWithExtendedInfo,
                    isLoading = false
                )
            }
        }
    }

    private fun addProductToCart() {
        viewModelScope.launch {
            state.value.product?.let {
                addProductToCart(product = uiToDomainProductMapper.map(it))
            }
        }
    }

    private fun removeProductFromCart() {
        viewModelScope.launch {
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