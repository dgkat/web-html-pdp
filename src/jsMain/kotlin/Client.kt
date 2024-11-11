import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

// Define a simple Product data model
@Serializable
data class Product(val id: Int, val name: String)

/*private val jsonBuilder = Json { ignoreUnknownKeys = true }

suspend fun fetchProductById(id: Int = 3): String {
    val response = window.fetch("http://localhost:8080/productById1").await()
    val json = response.text().await()
    println(Product::class.js)
    val product = jsonBuilder.decodeFromString<Product>(json)
    println(Product::class.js)
    return product.name
}

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
            console.log("Success : $product")
        } catch (e: Exception) {
            console.error("Error fetching cat fact: ${e.message}")
        }
    }

}*/

fun main() {
    renderComposable(rootElementId = "root") {
        App()
    }
}

/*@Composable
fun App() {
    var count = remember { mutableStateOf(0) }

    Div {
        H1 { Text("Hello, Compose for Web!") }
        Button(
            attrs = {
                onClick { count.value++ }
            }
        ) {
            Text("Clicked ${count.value} times")
        }
    }
}*/

@Composable
fun App() {
    val scope = rememberCoroutineScope()
    var state by remember { mutableStateOf<CatFactState>(CatFactState.Loading) }

    // Fetch the cat fact when the composable first loads
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val fact = fetchCatFact()
                state = CatFactState.Success(fact)
            } catch (e: Exception) {
                state = CatFactState.Error("Failed to load cat fact")
            }
        }
    }
    var count = remember { mutableStateOf(0) }

    Div {
        H1 { Text("Hello, Compose for Web!") }
        Button(
            attrs = {
                onClick { count.value++ }
            }
        ) {
            Text("Clicked ${count.value} times")
        }
    }
    // Render UI based on the state
    when (state) {
        is CatFactState.Loading -> {
            Div {
                Text("Loading...")
                // You could replace this with an animated loading circle if needed
            }
        }
        is CatFactState.Success -> {
            Div(attrs = {classes("header")}) {
                Text("Cat Fact: ${(state as CatFactState.Success).fact}")
            }
        }
        is CatFactState.Error -> {
            Div {
                Text("Error: ${(state as CatFactState.Error).message}")
            }
        }
    }
}