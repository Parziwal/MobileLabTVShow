package hu.bme.aut.tvshowapp.ui.screen.list

import hu.bme.aut.tvshowapp.Config.Companion.IMAGE_PLACEHOLDER
import hu.bme.aut.tvshowapp.Config.Companion.IMAGE_PREFIX_URL
import hu.bme.aut.tvshowapp.model.TvShow
import hu.bme.aut.tvshowapp.network.TvShowApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowListRepository @Inject constructor(
    private val tvShowApi: TvShowApi,
) {
    suspend fun getPopularTvShows() = withContext(Dispatchers.IO) {
        tvShowApi.getPopularTvShows().results.map {
            TvShow(
                it.id,
                it.name,
                if (it.posterPath != null) IMAGE_PREFIX_URL + it.posterPath else IMAGE_PLACEHOLDER,
                it.voteAverage,
                it.firstAirDate
            )
        }
    }
}