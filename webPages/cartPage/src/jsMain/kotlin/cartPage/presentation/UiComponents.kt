package cartPage.presentation

import androidx.compose.runtime.Composable
import cartPage.navigation.navigateTo
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun CartPage() {
    Div(attrs = { style { padding(20.px); fontSize(20.px) } }) {
        println("visibility test Cart Page")
        H1 { Text("Cart Page") }
        Text("This is the cart page.")
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