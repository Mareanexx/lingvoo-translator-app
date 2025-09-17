package ru.mareanexx.feature_translate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.mareanexx.core.utils.network.tracker.NetworkMonitor
import ru.mareanexx.feature_translate.data.local.dao.FavoritesDao
import ru.mareanexx.feature_translate.data.local.dao.TranslationHistoryDao
import ru.mareanexx.feature_translate.data.remote.api.TranslationApi
import ru.mareanexx.feature_translate.data.repository.FavoritesRepositoryImpl
import ru.mareanexx.feature_translate.data.repository.TranslationHistoryRepositoryImpl
import ru.mareanexx.feature_translate.data.repository.TranslationRepositoryImpl
import ru.mareanexx.feature_translate.domain.repository.FavoritesRepository
import ru.mareanexx.feature_translate.domain.repository.TranslationHistoryRepository
import ru.mareanexx.feature_translate.domain.repository.TranslationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TranslationModule {
    @Singleton
    @Provides
    fun provideTranslationRepository(
        translationApi: TranslationApi,
        networkMonitor: NetworkMonitor
    ): TranslationRepository =
        TranslationRepositoryImpl(translationApi, networkMonitor)

    @Singleton
    @Provides
    fun provideFavoritesRepository(
        favoritesDao: FavoritesDao
    ): FavoritesRepository =
        FavoritesRepositoryImpl(favoritesDao)

    @Singleton
    @Provides
    fun provideTranslationHistoryRepository(
        translationHistoryDao: TranslationHistoryDao
    ): TranslationHistoryRepository =
        TranslationHistoryRepositoryImpl(translationHistoryDao)

    @Singleton
    @Provides
    fun provideTranslateApi(retrofit: Retrofit): TranslationApi = retrofit.create(TranslationApi::class.java)
}