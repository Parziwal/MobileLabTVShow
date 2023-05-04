package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastEpisodeToAirResult (
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: Long,
    val id: Long,
    val name: String,
    val overview: String,
    @SerialName("production_code")
    val productionCode: String,
    @SerialName("season_number")
    val seasonNumber: Long,
    @SerialName("still_path")
    val stillPath: String?,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Long
)