package hu.bme.aut.tvshowapp.persistence

import hu.bme.aut.tvshowapp.model.Review

interface ReviewDao {
    fun getTvShowReviews(tvShowId: Long): List<Review>
    fun addReview(review: Review)
    fun deleteReview(id: Long)
}