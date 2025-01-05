import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

/*
fun main() {
    */
/*startKoin {
        modules(
            listOf(
                appModule,
                productDetailPageModule
            )
        )
    }*//*

    //Sync commit
    try {
        renderComposable(rootElementId = "root") {
            println("testDB 1")
            //ProductDetailPageScreen()
        }
    }catch (e:Exception){
        println(e)
        throw e
    }
}*/
fun main() {
    renderComposable(rootElementId = "root") {
        Div {
            H1 { Text("Product") }
            Text("This is the product page.")
        }
    }
}