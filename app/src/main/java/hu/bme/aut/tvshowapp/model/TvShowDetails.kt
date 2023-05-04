package hu.bme.aut.tvshowapp.model

data class TvShowDetails(
    val id: Long,
    val name: String,
    val posterPath: String?,
    val voteAverage: Double,
    val firstAirDate: String,
    val genres: List<String>,
    val overview: String,
    val createdBy: List<String>,
    val seasons: List<Season>,
    val reviews: List<Review>,
)