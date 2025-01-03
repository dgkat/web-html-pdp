package productDetailPage.presentation.models

data class UiExtendedProductInfo(
    val features: List<UiFeature> ? = null,
    val isLoading: Boolean = true
)

data class UiFeature(
    val featureText: String
)