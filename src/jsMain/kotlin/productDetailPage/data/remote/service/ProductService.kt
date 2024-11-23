package productDetailPage.data.remote.service

import productDetailPage.data.remote.models.RemoteProduct

interface ProductService {
    suspend fun getProductById(): RemoteProduct
}