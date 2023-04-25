package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowListResult (
    val page: Long,
    val results: List<TvShowResult>,
    @SerialName("total_results")
    val totalResults: Long,
    @SerialName("total_pages")
    val totalPages: Long
)