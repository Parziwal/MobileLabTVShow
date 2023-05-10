package hu.bme.aut.tvshowapp.model

data class TvShowDetails(
    val id: Long,
    val name: String,
    val posterPath: String?,
    val voteAverage: Double,
    val firstAirDate: String,
    val genres: String,
    val overview: String,
    val createdBy: String,
    val seasons: List<Season>,
) {
    constructor() : this(0, "", "", 0.0, "", "", "", "", emptyList())
}