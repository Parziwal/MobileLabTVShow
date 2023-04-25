package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryResult (
    @SerialName("iso_3166_1")
    val iso3166_1: String,
    val name: String
)