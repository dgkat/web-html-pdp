package productDetailPage.presentation.models

import kotlinx.serialization.Serializable

@Serializable
data class UiProduct(
    val name: String,
    val id: Long = 0,
    val type: String,
    val imageUrl: String,
    val price: Float,
    val description: String
)