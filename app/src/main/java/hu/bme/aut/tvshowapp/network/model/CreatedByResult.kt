package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedByResult (
    val id: Long,
    @SerialName("credit_id")
    val creditId: String,
    val name: String,
    val gender: Long,
    @SerialName("profile_path")
    val profilePath: String?
)