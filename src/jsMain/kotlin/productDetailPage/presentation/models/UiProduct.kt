package productDetailPage.presentation.models

data class UiProduct(
    val name: String,
    val id: String,
    val type: String,
    val imageUrl: String,
    val price: Float,
    val description: String,
    val extendedProductInfo: UiExtendedProductInfo?
)