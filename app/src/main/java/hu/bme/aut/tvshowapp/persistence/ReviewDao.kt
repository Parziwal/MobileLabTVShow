package hu.bme.aut.tvshowapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.bme.aut.tvshowapp.persistence.model.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * from reviews WHERE tvShowId = :tvShowId")
    fun getTvShowReviews(tvShowId: Long): Flow<List<ReviewEntity>>
    @Insert
    suspend fun addReview(review: ReviewEntity)
    @Query("DELETE FROM reviews WHERE id = :id")
    suspend fun deleteReviewById(id: Long)
}