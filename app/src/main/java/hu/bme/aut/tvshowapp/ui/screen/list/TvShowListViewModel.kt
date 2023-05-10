package hu.bme.aut.tvshowapp.ui.screen.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val repository: TvShowListRepository,
): ViewModel() {
    var tvShowListUiState: TvShowListUiState by mutableStateOf(TvShowListUiState.Loading)
        private set

    init {
        getPopularTvShows()
    }

    private fun getPopularTvShows() {
        viewModelScope.launch {
            tvShowListUiState = try {
                val result = repository.getPopularTvShows()
                TvShowListUiState.Success(result)
            } catch (e: Exception) {
                TvShowListUiState.Error
            }
        }
    }
}