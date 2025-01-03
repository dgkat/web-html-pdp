package cartPage.presentation

import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Div {
            H1 { Text("Cart Page") }
            Text("This is the cart page.")
        }
    }
}