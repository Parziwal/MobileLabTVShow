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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowDetailsRepository @Inject constructor(
    private val reviewDao: ReviewDao,
    private val tvShowApi: TvShowApi,
) {

    fun getTvShowDetails(id: Long) = flow {
        reviewDao.getTvShowReviews(id).map { reviews ->
            reviews.map { Review(it.id, it.rating, it.comment) }
        }.collect { reviews ->
            val tvShowDetails = tvShowApi.getTvShowDetails(id).let { tvShow ->
                TvShowDetails(
                    tvShow.id,
                    tvShow.name,
                    if (tvShow.posterPath != null) IMAGE_PREFIX_URL + tvShow.posterPath else Config.IMAGE_PLACEHOLDER,
                    tvShow.voteAverage,
                    tvShow.firstAirDate,
                    tvShow.genres.map { it.name },
                    tvShow.overview,
                    tvShow.createdBy.map { it.name },
                    tvShow.seasons.map {
                        Season(
                            it.seasonNumber,
                            it.name,
                            it.overview,
                            it.airDate,
                            it.episodeCount
                        )
                    },
                    reviews,
                )
            }
            emit(tvShowDetails);
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