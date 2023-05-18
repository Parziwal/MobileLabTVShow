package hu.bme.aut.tvshowapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hu.bme.aut.tvshowapp.ui.screen.details.TvShowDetailsScreen
import hu.bme.aut.tvshowapp.ui.screen.list.TvShowListScreen

@Composable
fun TvShowAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "tvShowList"
    ) {
        composable("tvShowList") {
            TvShowListScreen(navController)
        }

        composable("tvShow/{tvShowId}",
            arguments = listOf(
                navArgument("tvShowId"){type = NavType.LongType}
            )
        ) {
            val tvShowId = it.arguments?.getLong("tvShowId")
            tvShowId?.let {
                TvShowDetailsScreen(navController)
            }
        }
    }
}