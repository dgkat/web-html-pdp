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
        when (isInCart) {
            IsInCartEnum.IN_CART -> AddToCartButton(
                buttonText = "Remove from Cart",
                buttonClass = "remove-from-cart",
                onClick = { onEvent(ProductDetailPageEvent.RemoveFromCart) }
            )
            IsInCartEnum.NOT_IN_CART -> AddToCartButton(
                buttonText = "Add to cart",
                buttonClass = "add-to-cart",
                onClick = { onEvent(ProductDetailPageEvent.AddToCart) }
            )
            IsInCartEnum.LOADING -> AddToCartButton(
                buttonText = "Loading",
                buttonClass = "remove-from-cart",
                onClick = {  }
            )
        }

        /*Button(
            attrs = {
                classes(if (isInCart == IsInCartEnum.IN_CART) "remove-from-cart" else "add-to-cart")
                onClick {
                    val event = if (isInCart == IsInCartEnum.IN_CART) {
                        ProductDetailPageEvent.RemoveFromCart
                    } else {
                        ProductDetailPageEvent.AddToCart
                    }
                    onEvent(event)
                }
            }
        ) {
            Text(if (isInCart == IsInCartEnum.IN_CART) "Remove from Cart" else "Add to Cart")
        }*/
    }
}

@Composable
fun AddToCartButton(
    buttonText: String,
    buttonClass: String,
    onClick: () -> Unit
) {
    Button(
        attrs = {
            classes(buttonClass)
            onClick{
                onClick()
            }
        }
    ) {
        Text(buttonText)
    }
}