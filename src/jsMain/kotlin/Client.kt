import dev.fritz2.core.render
import dev.fritz2.core.storeOf
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Define a simple Product data model
@Serializable
data class Product(val id: Int, val name: String)

private val jsonBuilder = Json { ignoreUnknownKeys = true }

suspend fun fetchProductById(id: Int = 3): String {
    val response = window.fetch("http://localhost:8080/productById1").await()
    val json = response.text().await()
    println(Product::class.js)
    val product = jsonBuilder.decodeFromString<Product>(json)
    println(Product::class.js)
    return product.name
}

val productList = storeOf(initialData = emptyList<Product>(), job = SupervisorJob())

val scope = MainScope()

fun main() {

    scope.launch {
        try {
            val product = fetchProductById() // Call to fetch the cat fact
            val updatedList = listOf(Product(0, product)) + listOf(
                Product(1, "Laptop"),
                Product(2, "Smartphone"),
                Product(3, "Headphones")
            )
            productList.update(updatedList) // Update the product list
            console.log("Success : $product")
        } catch (e: Exception) {
            console.error("Error fetching cat fact: ${e.message}")
        }
    }
    // Render the UI with Fritz2
    render {
        h1 { +"Product List" }

        // Render each product as a list item
        productList.renderEach(Product::id) { product ->
            div("product-item") {
                h3 { +product.current.name } // Correct way to access the name property
                p { +"Product ID: ${product.current.id}" }
            }
        }
    }
}
