package ru.mareanexx.feature_translate.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.mareanexx.feature_translate.data.local.db.LingvooDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LingvooDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = LingvooDatabase::class.java,
            name = "lingvoo_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoritesDao(db: LingvooDatabase) = db.favoritesDao()

    @Singleton
    @Provides
    fun provideTranslationHistoryDao(db: LingvooDatabase) =
        db.translationHistoryDao()
}