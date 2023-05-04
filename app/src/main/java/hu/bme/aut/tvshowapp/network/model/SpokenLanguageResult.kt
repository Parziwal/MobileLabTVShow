package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguageResult (
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso639_1: String,
    val name: String
)