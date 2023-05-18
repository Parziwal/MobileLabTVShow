package hu.bme.aut.tvshowapp.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tvShowId: Long,
    val rating: Double,
    val comment: String,
)