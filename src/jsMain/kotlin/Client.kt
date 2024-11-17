import androidx.compose.runtime.*
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

@Serializable
data class Product(
    val name: String,
    val id: Long = 0,
    val type: String,
    val imageUrl: String,
    val price: Float,
    val description: String
)

sealed class ProductState {
    data object Loading : ProductState()
    data class Success(val product: Product) : ProductState()
    data class Error(val message: String) : ProductState()
}

private val jsonBuilder = Json { ignoreUnknownKeys = true }

suspend fun fetchProduct(): Product {
    val response = window.fetch("https://catfact.ninja/fact").await()
    val json = response.text().await()
    val fact = jsonBuilder.decodeFromString<CatFactResponse>(json).fact
    return Product(
        name = "Sample Product",
        id = 1L,
        type = "Electronics",
        imageUrl = "https://via.placeholder.com/150",
        price = 29.99f,
        description = fact
    )
}


fun main() {
    renderComposable(rootElementId = "root") {
        App()
    }
}


@Composable
fun App() {
    val scope = rememberCoroutineScope()
    var state by remember { mutableStateOf<ProductState>(ProductState.Loading) }

    // Fetch the product when the composable first loads
    LaunchedEffect(Unit) {
        scope.launch {
            state = try {
                val product = fetchProduct()
                ProductState.Success(product)
            } catch (e: Exception) {
                ProductState.Error("Failed to load product")
            }
        }
    }

    when (state) {
        is ProductState.Loading -> {
            Div {
                Text("Loading...")
            }
        }

        is ProductState.Success -> {
            val product = (state as ProductState.Success).product
            ProductDetail(product)
        }

        is ProductState.Error -> {
            Div {
                Text("Error: ${(state as ProductState.Error).message}")
            }
        }
    }
}

@Composable
fun ProductDetail(product: Product) {
    Div(attrs = { classes("product-detail") }) {
        Img(src = product.imageUrl, attrs = {
            classes("product-image")
            attr("alt", "Product Image")
        })
        H2 { Text(product.name) }
        P { Text("Price: $${product.price}") }
        P { Text(product.description) }
        Button(attrs = {
            onClick {
                // Navigate to the cart (to be implemented)
                console.log("Add to Cart clicked for ${product.name}")
            }
        }) {
            Text("Add to Cart")
        }
    }
}