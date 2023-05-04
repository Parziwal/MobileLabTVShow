package hu.bme.aut.tvshowapp.model

data class Season(
    val seasonNumber: Long,
    val name: String,
    val overview: String,
    val airDate: String,
    val episodeCount: Long,
)