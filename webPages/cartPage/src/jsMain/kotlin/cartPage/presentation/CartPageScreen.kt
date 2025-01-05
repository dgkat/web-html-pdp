package cartPage.presentation

import cartPage.test.CartStringProvider
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Div {
            val a = CartStringProvider().getCartString()
            H1 { Text("Cart Page") }
            Text("This is the cart paGe $a.")

            Text("Added $a")
        }
    }
}