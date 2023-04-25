package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AddOrEditReview(
    val rating: Double,
    val comment: String,
)