package productDetailPage.presentation.uiComponents

import androidx.compose.runtime.Composable
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