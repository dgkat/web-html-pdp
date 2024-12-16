package productDetailPage.presentation.mappers

import productDetailPage.domain.models.ExtendedProductInfo
import productDetailPage.domain.models.Feature
import productDetailPage.domain.models.Product
import productDetailPage.presentation.models.UiExtendedProductInfo
import productDetailPage.presentation.models.UiFeature
import productDetailPage.presentation.models.UiProduct

class DomainToUiProductMapper(
    private val domainToUiExtendedProductInfoMapper: DomainToUiExtendedProductInfoMapper
) {
    fun map(product: Product): UiProduct {
        return UiProduct(
            name = product.name,
            id = product.id,
            type = product.type,
            imageUrl = product.imageUrl,
            price = product.price,
            description = product.description,
            extendedProductInfo = product.extendedProductInfo?.let {
                domainToUiExtendedProductInfoMapper.map(
                    it
                )
            }
        )
    }
}

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