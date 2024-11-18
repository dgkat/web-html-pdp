package productDetailPage.data.remote.client

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import productDetailPage.data.remote.models.RemoteProduct

class ProductClientImpl(private val json: Json) : ProductClient {
    override suspend fun getProductById(): RemoteProduct {
        val response = window.fetch("https://catfact.ninja/fact").await()
        val jsonText = response.text().await()
        return json.decodeFromString(jsonText)
    }
}