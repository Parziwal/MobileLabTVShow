@file:OptIn(ExperimentalMaterial3Api::class)

package hu.bme.aut.tvshowapp.ui.screen.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.tvshowapp.model.TvShow
import hu.bme.aut.tvshowapp.ui.screen.list.components.TvShowItemCard
import hu.bme.aut.tvshowapp.ui.shared.ErrorView
import hu.bme.aut.tvshowapp.ui.shared.LoadingView

@Composable
fun TvShowListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    tvShowListViewModel: TvShowListViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Popular TV Shows")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                actions = {
                    IconButton(onClick = {
                        throw RuntimeException("Test Crash")
                    }) {
                        Icon(Icons.Filled.Warning, "Crash")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            when (tvShowListViewModel.tvShowListUiState) {
                is TvShowListUiState.Loading -> LoadingView()
                is TvShowListUiState.Success -> ResultView(
                    (tvShowListViewModel.tvShowListUiState as TvShowListUiState.Success).tvShows,
                    navController)
                is TvShowListUiState.Error -> ErrorView(modifier)
            }
        }
    }
}

@Composable
fun ResultView(
    tvShows: List<TvShow>,
    navController: NavController,
) {
    LazyColumn() {
        items(tvShows) { tvShow ->
            TvShowItemCard(tvShow) {
                navController.navigate("tvShow/${tvShow.id}")
            }
        }
    }
}