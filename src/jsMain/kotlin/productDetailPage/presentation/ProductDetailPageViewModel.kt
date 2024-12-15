package productDetailPage.presentation

import core.presentation.CommonViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import productDetailPage.domain.useCases.AddProductToCart
import productDetailPage.domain.useCases.GetExtendedProductInfoById
import productDetailPage.domain.useCases.GetProductByIdUseCase
import productDetailPage.domain.useCases.RemoveProductFromCart
import productDetailPage.presentation.mappers.DomainToUiExtendedProductInfoMapper
import productDetailPage.presentation.mappers.DomainToUiProductMapper
import productDetailPage.presentation.mappers.UiToDomainProductMapper
import productDetailPage.presentation.models.UiProduct

class ProductDetailPageViewModel(
    private val addProductToCart: AddProductToCart,
    private val removeProductFromCart: RemoveProductFromCart,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getExtendedProductInfoById: GetExtendedProductInfoById,
    private val domainToUiProductMapper: DomainToUiProductMapper,
    private val domainToUiExtendedProductInfoMapper: DomainToUiExtendedProductInfoMapper,
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

            val uiIsInCart = if (uiProduct.isInCart) {
                IsInCartEnum.IN_CART
            } else {
                IsInCartEnum.NOT_IN_CART
            }
            _state.update {
                it.copy(
                    product = uiProduct,
                    isLoading = false,
                    isInCart = uiIsInCart
                )
            }

            val extendedProductInfo = getExtendedProductInfoById(customId)

            val uiExtendedProductInfo = domainToUiExtendedProductInfoMapper.map(
                extendedProductInfo
            ).copy(
                isLoading = false
            )
            _state.update {
                it.copy(
                    extendedProductInfo = uiExtendedProductInfo
                )
            }
        }
    }

    private fun addProductToCart() {
        _state.update { state ->
            state.copy(isInCart = IsInCartEnum.LOADING)
        }
        viewModelScope.launch {
            state.value.product?.let {
                addProductToCart(product = uiToDomainProductMapper.map(it))
            }
            _state.update { state ->
                state.copy(isInCart = IsInCartEnum.IN_CART)
            }
        }
    }

    private fun removeProductFromCart() {
        _state.update { state ->
            state.copy(isInCart = IsInCartEnum.LOADING)
        }
        viewModelScope.launch {
            state.value.product?.let { uiProduct: UiProduct ->
                removeProductFromCart(uiProduct.id)
            }
            _state.update { state ->
                state.copy(isInCart = IsInCartEnum.NOT_IN_CART)
            }
        }
    }

    fun onEvent(event: ProductDetailPageEvent) {
        when (event) {
            is ProductDetailPageEvent.AddToCart -> addProductToCart()

            is ProductDetailPageEvent.RemoveFromCart -> removeProductFromCart()
        }
    }
}