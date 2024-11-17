import org.jetbrains.compose.web.renderComposable
import productDetailPage.presentation.ProductDetailPageScreen

fun main() {
    renderComposable(rootElementId = "root") {
        ProductDetailPageScreen()
    }
}