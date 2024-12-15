package productDetailPage.presentation

import productDetailPage.presentation.models.UiExtendedProductInfo
import productDetailPage.presentation.models.UiProduct

data class ProductDetailPageState(
    val product: UiProduct? = null,
    val extendedProductInfo: UiExtendedProductInfo = UiExtendedProductInfo(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val isInCart: IsInCartEnum = IsInCartEnum.LOADING
)

enum class IsInCartEnum {
    IN_CART, NOT_IN_CART, LOADING
}