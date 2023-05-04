package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResult (
    val name: String,
    val id: Long,
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("origin_country")
    val originCountry: String
)