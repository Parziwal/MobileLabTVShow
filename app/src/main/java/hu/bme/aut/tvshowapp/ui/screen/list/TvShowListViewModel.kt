package hu.bme.aut.tvshowapp.ui.screen.list

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.tvshowapp.extensions.createLog
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val repository: TvShowListRepository,
): ViewModel() {
    var tvShowListUiState: TvShowListUiState by mutableStateOf(TvShowListUiState.Loading)
        private set
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    init {
        getPopularTvShows()
    }

    private fun getPopularTvShows() {
        viewModelScope.launch {
            tvShowListUiState = try {
                val result = repository.getPopularTvShows()
                firebaseAnalytics.createLog(
                    "getPopularTvShows",
                    "Successfully listed popular TV Shows",
                    "TvShowListViewModel",
                    "getPopularTvShows"
                )
                TvShowListUiState.Success(result)
            } catch (e: Exception) {
                TvShowListUiState.Error
            }
        }
    }
}