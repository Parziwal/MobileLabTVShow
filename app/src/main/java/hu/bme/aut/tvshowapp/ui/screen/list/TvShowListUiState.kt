package hu.bme.aut.tvshowapp.ui.screen.list

import hu.bme.aut.tvshowapp.model.TvShow

sealed interface TvShowListUiState {
    data class Success(val tvShows:  List<TvShow>) : TvShowListUiState
    object Error : TvShowListUiState
    object Loading : TvShowListUiState
}