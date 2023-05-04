package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowResult (
    @SerialName("poster_path")
    val posterPath: String?,
    val popularity: Double,
    val id: Long,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("vote_average")
    val voteAverage: Double,
    val overview: String,
    @SerialName("first_air_date")
    val firstAirDate: String,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("genre_ids")
    val genreIds: List<Long>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("vote_count")
    val voteCount: Long,
    val name: String,
    @SerialName("original_name")
    val originalName: String
)