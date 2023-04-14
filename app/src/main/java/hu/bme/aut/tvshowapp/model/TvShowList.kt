package hu.bme.aut.tvshowapp.model

data class TvShowList(
    val page: Int,
    val results: List<TvShow>,
    val total_results: Int,
    val total_pages: Int,
)