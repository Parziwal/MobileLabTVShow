package hu.bme.aut.tvshowapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshowapp.persistence.AppDatabase
import hu.bme.aut.tvshowapp.persistence.ReviewDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.getDatabase(application)
    }

    @Provides
    @Singleton
    fun provideReviewDao(AppDatabase: AppDatabase): ReviewDao {
        return AppDatabase.reviewDao()
    }
}