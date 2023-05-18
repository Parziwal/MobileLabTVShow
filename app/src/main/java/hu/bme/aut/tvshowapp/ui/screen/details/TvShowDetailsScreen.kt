@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package hu.bme.aut.tvshowapp.ui.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hu.bme.aut.tvshowapp.model.TvShowDetails
import hu.bme.aut.tvshowapp.ui.screen.details.components.AddReviewDialog
import hu.bme.aut.tvshowapp.ui.screen.details.components.ReviewItemCard
import hu.bme.aut.tvshowapp.ui.screen.details.components.SeasonItemCard
import hu.bme.aut.tvshowapp.ui.shared.ErrorView
import hu.bme.aut.tvshowapp.ui.shared.LoadingView
import hu.bme.aut.tvshowapp.ui.shared.RatingBar

@Composable
fun TvShowDetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    tvShowDetailsViewModel: TvShowDetailsViewModel = hiltViewModel()
) {
    when (tvShowDetailsViewModel.tvShowDetailsUiState) {
        is TvShowDetailsUiState.Loading -> LoadingView()
        is TvShowDetailsUiState.Success -> ResultView(
                (tvShowDetailsViewModel.tvShowDetailsUiState as TvShowDetailsUiState.Success).tvShow,
                tvShowDetailsViewModel,
                navController,
                modifier,
            )
        is TvShowDetailsUiState.Error -> ErrorView(modifier)
    }
}

@Composable
fun ResultView(
    tvShow: TvShowDetails,
    tvShowDetailsViewModel: TvShowDetailsViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var showAddDialog by rememberSaveable {
        mutableStateOf(false)
    }
    val reviews by tvShowDetailsViewModel.getReviews().collectAsState(emptyList())

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(tvShow.name)
                },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                actions = {
                    IconButton(onClick = {
                        showAddDialog = true
                    }) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tvShow.posterPath)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.7f)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp),
                )
                Text(
                    text = tvShow.genres,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "First air date: ${tvShow.firstAirDate}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Created by: ${tvShow.createdBy}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Overview",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(text = tvShow.overview)
                Text(
                    text = "Seasons",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 10.dp)
                )
                tvShow.seasons.forEach { season ->
                    SeasonItemCard(season)
                }
                Text(
                    text = "Reviews",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Row() {
                    Text("Average")
                    Spacer(Modifier.weight(1.0f))
                    RatingBar(rating = 3f)
                    Spacer(Modifier.width(10.dp))
                    Text("7.8")
                }
                reviews.forEach { review ->
                    ReviewItemCard(review) {
                        tvShowDetailsViewModel.deleteReview(it)
                    }
                }
            }
        }
        if (showAddDialog) {
            AddReviewDialog(
                addReview = {
                    tvShowDetailsViewModel.addReview(it)
                },
                dialogDismiss = { showAddDialog = false })
        }
    }
}