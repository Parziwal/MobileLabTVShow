package hu.bme.aut.tvshowapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowDetailsResult (
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("created_by")
    val createdBy: List<CreatedByResult>,
    @SerialName("episode_run_time")
    val episodeRunTime: List<Long>,
    @SerialName("first_air_date")
    val firstAirDate: String,
    val genres: List<GenreResult>,
    val homepage: String,
    val id: Long,
    @SerialName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerialName("last_air_date")
    val lastAirDate: String,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAirResult,
    val name: String,
    val networks: List<NetworkResult>,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Long,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Long,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<NetworkResult>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryResult>,
    val seasons: List<SeasonResult>,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResult>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Long
)