package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreResult (
    val id: Long,
    val name: String
)