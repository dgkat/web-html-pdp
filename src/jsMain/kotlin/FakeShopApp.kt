import org.jetbrains.compose.web.renderComposable
import org.koin.core.context.GlobalContext.startKoin
import productDetailPage.di.productDetailPageModule
import productDetailPage.presentation.ProductDetailPageScreen

fun main() {
    startKoin {
        modules(productDetailPageModule)
    }

    renderComposable(rootElementId = "root") {
        ProductDetailPageScreen()
    }
}