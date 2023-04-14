package hu.bme.aut.tvshowapp.model

data class Review(
    val id: Long,
    val rating: Double,
    val comment: String,
    val movieId: Long,
)