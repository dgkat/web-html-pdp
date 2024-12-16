package productDetailPage.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePopulationData(
    val data: List<RemotePopulationEntry>
)

@Serializable
data class RemotePopulationEntry(
    @SerialName("Population") val population: Int
)