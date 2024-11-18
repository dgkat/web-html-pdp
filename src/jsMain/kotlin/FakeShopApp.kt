import org.jetbrains.compose.web.renderComposable
import org.koin.core.context.GlobalContext.startKoin
import org.koin.mp.KoinPlatform.getKoin
import productDetailPage.di.productDetailPageModule
import productDetailPage.presentation.ProductDetailPageScreen
import productDetailPage.presentation.ProductDetailPageViewModel

fun main() {
    startKoin {
        modules(productDetailPageModule)
    }

    val pdpVm: ProductDetailPageViewModel = getKoin().get()
    renderComposable(rootElementId = "root") {
        ProductDetailPageScreen()
    }
}