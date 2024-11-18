package productDetailPage.domain.models

data class Product(
    val name: String,
    val id: String,
    val type: String,
    val imageUrl: String,
    val price: Float,
    val description: String
)