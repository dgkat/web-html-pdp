package productDetailPage.presentation.uiComponents

import androidx.compose.runtime.Composable
import core.data.local.openDatabase
import core.data.local.testDatabase
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.*
import productDetailPage.presentation.ProductDetailPageEvent
import productDetailPage.presentation.models.UiProduct
import web.idb.indexedDB

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
    println("test pdp 1")
    /*val scope = MainScope()
    println("test pdp 2")
    scope.launch {
        println("test pdp 3")
        testDatabase()
        println("test pdp 4")
    }*/
    println("test pdp 5")


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