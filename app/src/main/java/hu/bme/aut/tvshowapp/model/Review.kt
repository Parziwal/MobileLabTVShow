package hu.bme.aut.tvshowapp.model

data class Review(
    val id: Long = 0,
    val rating: Int,
    val comment: String
)