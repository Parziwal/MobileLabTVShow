package hu.bme.aut.tvshowapp.model

data class TvShowDetails(
    val id: Long,
    val name: String,
    val poster_path: String?,
    val vote_average: Double,
    val first_air_date: String,
    val genres: List<Genre>,
    val overview: String,
    val created_by: List<Creator>,
    val seasons: List<Season>,
)