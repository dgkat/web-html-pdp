package productDetailPage.domain.models

data class ExtendedProductInfo(
    val features: List<Feature>
)

data class Feature(
    val featureText: String
)

