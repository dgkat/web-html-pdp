package productDetailPage.presentation.uiComponents

import androidx.compose.runtime.Composable
import core.data.DBTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.*
import org.koin.mp.KoinPlatform.getKoin
import productDetailPage.presentation.ProductDetailPageEvent
import productDetailPage.presentation.models.UiProduct

@Composable
fun ProductDetailPage(uiProduct: UiProduct, isInCart: Boolean, onEvent: (ProductDetailPageEvent) -> Unit) {
    Div(attrs = { classes("product-detail-container") }) {
        Div(attrs = { classes("product-image-container") }) {
            Img(src = uiProduct.imageUrl, attrs = {
                classes("product-image")
                attr("alt", "Product Image")
            })
        }

        Div(attrs = { classes("product-info-container") }) {
            println("testDB 2")
            H2 { Text(uiProduct.name) }
            P { Text(uiProduct.description) }

            H3 { Text("Features:") }
            Ul {
                uiProduct.extendedProductInfo?.features?.forEach { feature ->
                    Li { Text(feature.featureText) }
                }
            }
        }
    }

    Div(attrs = { classes("floating-bar") }) {
        Div(attrs = { classes("price") }) {
            Text("Price: $${uiProduct.price}")
        }
        Button(
            attrs = {
                classes(if (isInCart) "remove-from-cart" else "add-to-cart")
                onClick {
                    val event = if (isInCart) {
                        ProductDetailPageEvent.RemoveFromCart(uiProduct.id)
                    } else {
                        ProductDetailPageEvent.AddToCart(uiProduct.id)
                    }
                    onEvent(event)
                }
            }
        ) {
            Text(if (isInCart) "Remove from Cart" else "Add to Cart")
        }
    }
}