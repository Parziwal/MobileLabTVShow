package hu.bme.aut.tvshowapp.model

data class Season(
    val id: Long,
    val season_number: Long,
    val name: String,
    val overview: String,
    val air_date: String,
    val episode_count: Long,
)