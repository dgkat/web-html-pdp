package core.data.local.models

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable

@Serializable
data class LocalProduct(
    val name: String,
    val id: String,
    val type: String,
    val imageUrl: String,
    val price: Float,
    val description: String,
    val timestamp: Long = Clock.System.now().toEpochMilliseconds()
)
