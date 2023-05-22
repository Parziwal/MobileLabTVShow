package hu.bme.aut.tvshowapp.ui.screen.details

import hu.bme.aut.tvshowapp.Config
import hu.bme.aut.tvshowapp.Config.Companion.IMAGE_PREFIX_URL
import hu.bme.aut.tvshowapp.model.Review
import hu.bme.aut.tvshowapp.model.Season
import hu.bme.aut.tvshowapp.model.TvShowDetails
import hu.bme.aut.tvshowapp.network.TvShowApi
import hu.bme.aut.tvshowapp.persistence.ReviewDao
import hu.bme.aut.tvshowapp.persistence.model.ReviewEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowDetailsRepository @Inject constructor(
    private val reviewDao: ReviewDao,
    private val tvShowApi: TvShowApi,
) {

    suspend fun getTvShowDetails(id: Long): TvShowDetails {
        return tvShowApi.getTvShowDetails(id).let { tvShow ->
            TvShowDetails(
                tvShow.id,
                tvShow.name,
                if (tvShow.posterPath != null) IMAGE_PREFIX_URL + tvShow.posterPath else Config.IMAGE_PLACEHOLDER,
                tvShow.voteAverage,
                tvShow.firstAirDate,
                tvShow.genres.joinToString(separator = ", ") { it.name },
                tvShow.overview,
                tvShow.createdBy.joinToString(separator = ", ") { it.name },
                tvShow.seasons.map {
                    Season(
                        it.seasonNumber,
                        it.name,
                        it.overview,
                        it.airDate?.split('-')?.get(0) ?: "-",
                        if (it.posterPath != null) IMAGE_PREFIX_URL + it.posterPath else Config.IMAGE_PLACEHOLDER,
                        it.episodeCount
                    )
                },
            )
        }
    }

    fun getTvShowReviews(tvShowId: Long): Flow<List<Review>> {
        return reviewDao.getTvShowReviews(tvShowId).map { reviews ->
            reviews.map { Review(it.id, it.rating, it.comment) }
        }
    }

    suspend fun addReviewToTvShow(tvShowId: Long, review: Review) = withContext(Dispatchers.IO) {
        reviewDao.addReview(
            ReviewEntity(
                tvShowId = tvShowId,
                rating = review.rating,
                comment = review.comment
            )
        )
    }

    suspend fun deleteReviewById(id: Long) = withContext(Dispatchers.IO) {
        reviewDao.deleteReviewById(id)
    }
}