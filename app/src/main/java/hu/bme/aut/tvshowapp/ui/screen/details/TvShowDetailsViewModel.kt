package hu.bme.aut.tvshowapp.ui.screen.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val repository: TvShowDetailsRepository,
): ViewModel() {
}