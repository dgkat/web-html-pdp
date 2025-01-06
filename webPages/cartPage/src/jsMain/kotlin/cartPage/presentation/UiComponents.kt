package cartPage.presentation

import androidx.compose.runtime.Composable
import cartPage.navigation.navigateTo
import kotlinx.browser.window
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

@Composable
fun CartPage() {
    // Get the full query string (e.g., "?productId=123&testParam=hello")
    val queryString = window.location.search

    // Function to parse query parameters
    fun getQueryParam(name: String): String? {
        val params = queryString.removePrefix("?").split("&")
        for (param in params) {
            val (key, value) = param.split("=").let { it.getOrNull(0) to it.getOrNull(1) }
            if (key == name) return value
        }
        return null
    }

    // Retrieve parameters (null if not present)
    val productId = getQueryParam("productId")
    val testParam = getQueryParam("testParam")

    // Display values
    Div(attrs = { style { padding(20.px); fontSize(20.px) } }) {
        H2 { Text("Cart Page") }
        Text("Product ID: ${productId ?: "Not Provided"}")
        Br()
        Text("Test Param: ${testParam ?: "Not Provided"}")
        Br()
        Button(attrs = { onClick { navigateTo("/cart/payment") } }) {
            Text("Go to Payment")
        }
    }
}

@Composable
fun PaymentPage() {
    Div(attrs = { style { padding(20.px); fontSize(20.px) } }) {
        println("visibility test Payment Page")
        H1 { Text("Payment Page") }
        Text("Enter your payment details here.")
        Br()
        Button(attrs = { onClick { navigateTo("/cart/shipping-info") } }) {
            Text("Go to Shipping Info")
        }
        Br()
        Button(attrs = { onClick { navigateTo("/cart") } }) {
            Text("Back to Cart")
        }
    }
}

@Composable
fun ShippingInfoPage() {
    Div(attrs = { style { padding(20.px); fontSize(20.px) } }) {
        println("visibility test Shipping Info Page")
        H1 { Text("Shipping Info Page") }
        Text("Enter your shipping details here.")
        Br()
        Button(attrs = { onClick { navigateTo("/cart/payment") } }) {
            Text("Back to Payment")
        }
    }
}

@Composable
fun NotFoundPage() {
    Div(attrs = { style { padding(20.px); fontSize(20.px) } }) {
        println("visibility test 404 page")
        H1 { Text("404 - Page Not Found") }
        Text("The requested page does not exist.")
        Br()
        Button(attrs = { onClick { navigateTo("/cart") } }) {
            Text("Go to Cart")
        }
    }
}