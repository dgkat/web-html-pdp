package productDetailPage.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RemoteProduct(
    val fact: String,
    val length: Int
)