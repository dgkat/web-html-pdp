package productDetailPage.presentation.uiComponents

import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import productDetailPage.presentation.IsInCartEnum
import productDetailPage.presentation.ProductDetailPageEvent
import productDetailPage.presentation.models.UiExtendedProductInfo
import productDetailPage.presentation.models.UiProduct

@Composable
fun ProductDetailPage(
    uiProduct: UiProduct,
    extendedProductInfo: UiExtendedProductInfo,
    isInCart: IsInCartEnum,
    onEvent: (ProductDetailPageEvent) -> Unit
) {
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
                extendedProductInfo.features?.forEach { feature ->
                    Li { Text(feature.featureText) }
                }
            }
        }
    }

    Div(attrs = { classes("floating-bar") }) {
        Div(attrs = { classes("price") }) {
            Text("Price: $${uiProduct.price}")
        }
        //TODO rethink loading
        AddToCartButton(
            isInCart = isInCart,
            onEvent = onEvent
        )
    }
    Br()
    ARefToCartButton()
}

@Composable
fun AddToCartButton(
    isInCart: IsInCartEnum,
    onEvent: (ProductDetailPageEvent) -> Unit
) {
    Button(
        attrs = {
            classes(
                when (isInCart) {
                    IsInCartEnum.IN_CART -> "remove-from-cart"
                    IsInCartEnum.NOT_IN_CART -> "add-to-cart"
                    else -> "loading-cart"
                }
            )
            onClick {
                val event = when (isInCart) {
                    IsInCartEnum.IN_CART -> ProductDetailPageEvent.RemoveFromCart
                    IsInCartEnum.NOT_IN_CART -> ProductDetailPageEvent.AddToCart
                    else -> null
                }
                event?.let { onEvent(it) }
            }
        }
    ) {
        Text(
            when (isInCart) {
                IsInCartEnum.IN_CART -> "Remove from Cart"
                IsInCartEnum.NOT_IN_CART -> "Add to Cart"
                IsInCartEnum.LOADING -> "Loading"
            }
        )
    }
}

@Composable
fun GoToCartButton(){

    Button(attrs = {
        style {
            padding(10.px)
            fontSize(18.px)
            margin(10.px)
            cursor("pointer")
            backgroundColor(Color.blue)
            color(Color.white)
            borderRadius(5.px)
            border(0.px)
        }
        onClick {
            val productId = 123
            val testParam = "hello"  // Optional parameter

            // Build URL with parameters
            val url = "http://localhost:8080/cart?productId=$productId&testParam=$testParam"

            // Navigate to cart page
            window.location.href = url
        }
    }) {
        Text("Go to Cart")
    }
}

@Composable
fun ARefToCartButton(){
    val productId = 222
    val testParam = "hello"  // Optional parameter

    // Build URL with parameters
    val url = "http://localhost:8080/cart?productId=$productId&testParam=$testParam"
    A(href = url, attrs = {
        style {
            padding(10.px)
            fontSize(18.px)
            margin(10.px)
            cursor("pointer")
            backgroundColor(Color.blue)
            color(Color.white)
            borderRadius(5.px)
            border(0.px)
            textDecoration("none") // Make the link look like a button
        }
    }) {
        Text("Go to Cart")
    }
}