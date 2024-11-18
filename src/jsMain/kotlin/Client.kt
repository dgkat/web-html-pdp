import productDetailPage.presentation.models.UiProduct


sealed class ProductState {
    data object Loading : ProductState()
    data class Success(val uiProduct: UiProduct) : ProductState()
    data class Error(val message: String) : ProductState()
}
/*
private val jsonBuilder = Json { ignoreUnknownKeys = true }

suspend fun getProduct(): UiProduct {
    val response = window.fetch("https://catfact.ninja/fact").await()
    val json = response.text().await()
    val fact = jsonBuilder.decodeFromString<CatFactResponse>(json).fact
    return UiProduct(
        name = "Sample Product",
        id = 1L,
        type = "Electronics",
        imageUrl = "https://i.postimg.cc/LsXBmP7n/test-laptop-1.jpg",
        price = 29.99f,
        description = fact
    )
}*/





