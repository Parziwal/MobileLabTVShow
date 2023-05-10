package hu.bme.aut.tvshowapp.ui.screen.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hu.bme.aut.tvshowapp.model.TvShow
import hu.bme.aut.tvshowapp.ui.shared.RatingBar

@Composable
fun TvShowItemCard(
    tvShow: TvShow,
    itemClick: (String) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                itemClick("ssa")
            }
    ) {
        Row(
            modifier = Modifier
                .padding(0.dp)
                .height(150.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShow.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = tvShow.name,
                contentScale = ContentScale.Fit,
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = tvShow.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = tvShow.firstAirDate,
                    style = MaterialTheme.typography.bodyLarge
                )
                Row {
                    RatingBar(rating = tvShow.voteAverage.toFloat())
                    Spacer(Modifier.width(10.dp))
                    Text(tvShow.voteAverage.toString())
                }
            }
        }
    }
}