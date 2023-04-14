package hu.bme.aut.tvshowapp.model

data class TvShow(
    val id: Long,
    val name: String,
    val poster_path: String?,
    val vote_average: Double,
    val first_air_date: String,
)