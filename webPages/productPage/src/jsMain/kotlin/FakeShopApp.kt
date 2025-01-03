import commonMain.kotlin.webShared.core.di.appModule
import productDetailPage.di.productDetailPageModule
import productDetailPage.presentation.ProductDetailPageScreen
import org.jetbrains.compose.web.renderComposable
import org.koin.core.context.startKoin


fun main() {
    startKoin {
        modules(
            listOf(
                appModule,
                productDetailPageModule
            )
        )
    }
    //Sync commit
    try {
        renderComposable(rootElementId = "root") {
            println("testDB 1")
            ProductDetailPageScreen()
        }
    }catch (e:Exception){
        println(e)
        throw e
    }
}