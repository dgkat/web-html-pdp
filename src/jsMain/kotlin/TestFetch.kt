import kotlinx.serialization.Serializable

@Serializable
data class CatFactResponse(val fact: String, val length: Int)