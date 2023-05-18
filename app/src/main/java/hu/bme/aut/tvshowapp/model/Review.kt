package hu.bme.aut.tvshowapp.model

data class Review(
    val id: Long = 0,
    val rating: Double,
    val comment: String
)