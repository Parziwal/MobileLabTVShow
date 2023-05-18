package hu.bme.aut.tvshowapp.ui.screen.details

import hu.bme.aut.tvshowapp.model.TvShowDetails

sealed interface TvShowDetailsUiState {
    data class Success(val tvShow: TvShowDetails) : TvShowDetailsUiState
    object Error : TvShowDetailsUiState
    object Loading : TvShowDetailsUiState
}