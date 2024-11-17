package productDetailPage.presentation

import productDetailPage.presentation.models.UiProduct

data class ProductDetailPageState (
    val product: UiProduct,
    val isLoading: Boolean = true,
    val error: String? = null
)