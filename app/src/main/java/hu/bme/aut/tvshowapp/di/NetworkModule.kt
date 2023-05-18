package hu.bme.aut.tvshowapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshowapp.Config.Companion.API_URL
import hu.bme.aut.tvshowapp.network.ApiKeyInterceptor
import hu.bme.aut.tvshowapp.network.TvShowApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(API_URL)
            .addConverterFactory(
                Json{ ignoreUnknownKeys = true }.asConverterFactory(
                    "application/json"
                        .toMediaType()
                )
            )
            .build()

    }

    @Provides
    @Singleton
    fun provideTvShowApi(retrofit: Retrofit): TvShowApi {
        return retrofit.create(TvShowApi::class.java)
    }
}