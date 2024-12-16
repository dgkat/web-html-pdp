package productDetailPage.presentation

sealed class ProductDetailPageEvent {
    data object AddToCart : ProductDetailPageEvent()
    data object RemoveFromCart : ProductDetailPageEvent()
}