package productDetailPage.presentation.models

data class UiExtendedProductInfo(
    val features: List<UiFeature>
)

data class UiFeature(
    val featureText: String
)