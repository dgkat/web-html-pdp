package productDetailPage.presentation.mappers

import productDetailPage.domain.models.ExtendedProductInfo
import productDetailPage.domain.models.Feature
import productDetailPage.presentation.models.UiExtendedProductInfo
import productDetailPage.presentation.models.UiFeature

class DomainToUiExtendedProductInfoMapper(
    private val domainToUiFeatureMapper: DomainToUiFeatureMapper
) {
    fun map(extendedProductInfo: ExtendedProductInfo): UiExtendedProductInfo {
        return UiExtendedProductInfo(extendedProductInfo.features.map { domainToUiFeatureMapper.map(it) })
    }
}

class DomainToUiFeatureMapper {
    fun map(feature: Feature): UiFeature {
        return UiFeature(feature.featureText)
    }
}