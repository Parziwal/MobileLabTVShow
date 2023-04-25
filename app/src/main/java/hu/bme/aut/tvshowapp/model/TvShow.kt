package hu.bme.aut.tvshowapp.model

data class TvShow(
    val id: Long,
    val name: String,
    val posterPath: String?,
    val voteAverage: Double,
    val firstAirDate: String,
)