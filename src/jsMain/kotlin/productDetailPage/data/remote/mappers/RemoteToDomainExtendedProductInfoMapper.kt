package productDetailPage.data.remote.mappers

import productDetailPage.data.remote.models.RemotePopulationData
import productDetailPage.data.remote.models.RemotePopulationEntry
import productDetailPage.domain.models.ExtendedProductInfo
import productDetailPage.domain.models.Feature

class RemoteToDomainExtendedProductInfoMapper(
    private val remoteToDomainFeatureMapper: RemoteToDomainFeatureMapper
) {
    fun map(remotePopulationData: RemotePopulationData): ExtendedProductInfo {
        return ExtendedProductInfo(
            remotePopulationData.data.map {
                remoteToDomainFeatureMapper.map(it)
            }
        )
    }
}

class RemoteToDomainFeatureMapper {
    fun map(remotePopulationEntry: RemotePopulationEntry): Feature {
        return Feature(featureText = remotePopulationEntry.population.toString())
    }
}