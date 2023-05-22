package hu.bme.aut.tvshowapp.ui.screen.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.tvshowapp.extensions.createLog
import hu.bme.aut.tvshowapp.model.Review
import hu.bme.aut.tvshowapp.ui.screen.list.TvShowListUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val repository: TvShowDetailsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var tvShowDetailsUiState: TvShowDetailsUiState by mutableStateOf(TvShowDetailsUiState.Loading)
        private set

    private var tvShowId by Delegates.notNull<Long>()
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    init {
        savedStateHandle.get<Long>("tvShowId")?.let {
            tvShowId = it
            getTvShowDetails()
        }
    }

    private fun getTvShowDetails() {
        viewModelScope.launch {
            tvShowDetailsUiState = try {
                val result = repository.getTvShowDetails(tvShowId)
                firebaseAnalytics.createLog(
                    "getTvShowDetails",
                    "Successfully get ${result.name}",
                    "TvShowDetailsViewModel",
                    "getTvShowDetails"
                )
                TvShowDetailsUiState.Success(result)
            } catch (e: Exception) {
                Log.d("test",e.toString())
                TvShowDetailsUiState.Error
            }
        }
    }

    fun getReviews(): Flow<List<Review>> = repository.getTvShowReviews(tvShowId)

    fun addReview(review: Review) {
        viewModelScope.launch {
            repository.addReviewToTvShow(tvShowId, review)
        }
    }

    fun deleteReview(reviewId: Long) {
        viewModelScope.launch {
            repository.deleteReviewById(reviewId)
        }
    }
}