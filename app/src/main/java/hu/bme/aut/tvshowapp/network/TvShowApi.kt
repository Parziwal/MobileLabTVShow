package hu.bme.aut.tvshowapp.network

import hu.bme.aut.tvshowapp.network.model.AddOrEditReview
import hu.bme.aut.tvshowapp.network.model.TvShowDetailsResult
import hu.bme.aut.tvshowapp.network.model.TvShowListResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApi {
    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Long? = null,
        @Query("language") language: String? = null): TvShowListResult

    @GET("tv/{id}")
    suspend fun getTvShowDetails(
        @Path("id") id: Long,
        @Query("language") language: String? = null): TvShowDetailsResult

    // Mocked endpoints:
    @POST("tv/{id}/review")
    fun addReview(@Path("id") id: Long, @Body review: AddOrEditReview)

    @PUT("review/{id}")
    fun editReview(@Path("id") id: Long, @Body review: AddOrEditReview)

    @DELETE("review/{id}")
    fun deleteReview(@Path("id") id: Long)
}