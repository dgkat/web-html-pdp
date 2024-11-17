package productDetailPage.presentation.uiComponents

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.*
import productDetailPage.presentation.models.UiProduct

@Composable
fun ProductDetailPage(uiProduct: UiProduct) {
    Div(attrs = { classes("product-detail") }) {
        Img(src = uiProduct.imageUrl, attrs = {
            classes("product-image")
            attr("alt", "Product Image")
        })
        H2 { Text(uiProduct.name) }
        P { Text("Price: $${uiProduct.price}") }
        P { Text(uiProduct.description) }
        Button(attrs = {
            onClick {
                // Navigate to the cart (to be implemented)
                console.log("Add to Cart clicked for ${uiProduct.name}")
            }
        }) {
            Text("Add to Cart")
        }
    }
}