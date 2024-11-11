import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

suspend fun fetchCatFact(): String {
    val response = window.fetch("https://catfact.ninja/fact").await()
    val json = response.text().await()
    println(CatFactResponse::class.js)
    val catFact = Json.decodeFromString<CatFactResponse>(json)
    println(CatFactResponse::class.js)
    return catFact.fact
}

@Serializable
data class CatFactResponse(val fact: String, val length: Int)