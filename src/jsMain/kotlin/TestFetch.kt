import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class CatFactResponse(val fact: String, val length: Int)

sealed class CatFactState {
    object Loading : CatFactState()
    data class Success(val fact: String) : CatFactState()
    data class Error(val message: String) : CatFactState()
}

private val jsonBuilder = Json { ignoreUnknownKeys = true }

suspend fun fetchCatFact(): String {
    val response = window.fetch("https://catfact.ninja/fact").await()
    val json = response.text().await()
    val catFact = jsonBuilder.decodeFromString<CatFactResponse>(json)
    return catFact.fact
}