package productDetailPage.data.remote.client

import productDetailPage.data.remote.models.RemoteProduct

interface ProductClient {
    suspend fun getProductById(): RemoteProduct
}