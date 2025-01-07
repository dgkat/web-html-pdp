package productDetailPage.data.remote.service

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import productDetailPage.data.remote.models.RemotePopulationData
import productDetailPage.data.remote.models.RemoteProduct

class ProductServiceImpl(private val json: Json) : ProductService {
    override suspend fun getProductById(): RemoteProduct {
        val response = window.fetch("https://catfact.ninja/fact").await()
        val jsonText = response.text().await()
        return json.decodeFromString(jsonText)
    }

    //TODO split to different service when BE is implemented
    override suspend fun getExtendedProductInfo(): RemotePopulationData {

        val response = window.fetch("https://datausa.io/api/data?drilldowns=Nation&measures=Population").await()
        val jsonText = response.text().await()
        return json.decodeFromString<RemotePopulationData>(jsonText)
    }
}