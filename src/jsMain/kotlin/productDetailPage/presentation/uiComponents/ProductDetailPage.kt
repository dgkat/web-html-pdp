package productDetailPage.presentation.uiComponents

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.*
import productDetailPage.presentation.ProductDetailPageEvent
import productDetailPage.presentation.models.UiProduct

@Composable
fun ProductDetailPage(uiProduct: UiProduct, isInCart: Boolean, onEvent: (ProductDetailPageEvent) -> Unit) {
    Div(attrs = { classes("product-detail") }) {
        Img(src = uiProduct.imageUrl, attrs = {
            classes("product-image")
            attr("alt", "Product Image")
        })
        H2 { Text(uiProduct.name) }
        P { Text("Price: $${uiProduct.price}") }
        P { Text(uiProduct.description) }
        Button(
            attrs = {
                classes(if (isInCart) "remove-from-cart" else "add-to-cart")
                onClick {
                    // Navigate to the cart (to be implemented)
                    console.log("Add to Cart clicked for ${uiProduct.name}")
                    if (isInCart) {
                        onEvent(ProductDetailPageEvent.RemoveFromCart(uiProduct.id))
                    } else {
                        onEvent(ProductDetailPageEvent.AddToCart(uiProduct.id))
                    }
                }
            }
        ) {
            Text(if (isInCart) "Remove from Cart" else "Add to Cart")
        }
    }
}