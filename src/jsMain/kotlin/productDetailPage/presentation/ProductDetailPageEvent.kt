package productDetailPage.presentation

sealed class ProductDetailPageEvent {
    data class AddToCart(val id: String) : ProductDetailPageEvent()
    data class RemoveFromCart(val id: String) : ProductDetailPageEvent()
}