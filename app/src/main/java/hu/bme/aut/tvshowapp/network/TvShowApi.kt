package hu.bme.aut.tvshowapp.network

import hu.bme.aut.tvshowapp.model.Review
import hu.bme.aut.tvshowapp.model.TvShow
import hu.bme.aut.tvshowapp.model.TvShowDetails

interface TvShowApi {
    // GET
    fun getPopularTvShows(): List<TvShow>
    fun getTvShowDetails(id: Long): TvShowDetails

    // Mocked endpoints:
    // POST
    fun addReview(review: Review)
    // PUT
    fun updateReview(review: Review)
    // DELETE
    fun deleteReview(id: Long)
}