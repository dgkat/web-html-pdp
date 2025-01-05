package cartPage.presentation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cartPage.navigation.currentRoute
import cartPage.navigation.setupHistoryListener
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

/*
fun main() {
    */
/*renderComposable(rootElementId = "root") {
        Div {
            H1 { Text("Cart Page") }
            Text("This is the cart page.")
        }
    }*//*

    setupHistoryListener() // Listen for browser back/forward events
    render() // Initial render
}*/

fun main() {
    setupHistoryListener() // Setup listener for browser history

    renderComposable(rootElementId = "root") {
        val route by currentRoute.collectAsState() // Observe route changes
        Div {
            H1 { Text("Cart App") }
            when (route) {
                "/cart" -> CartPage()
                "/cart/payment" -> PaymentPage()
                "/cart/shipping-info" -> ShippingInfoPage()
                else -> NotFoundPage()
            }
        }
    }
}