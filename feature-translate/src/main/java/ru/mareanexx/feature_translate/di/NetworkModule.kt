package ru.mareanexx.feature_translate.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mareanexx.core.utils.BuildConfig
import ru.mareanexx.core.utils.adapters.LocalDateAdapter
import ru.mareanexx.core.utils.adapters.OffsetDateTimeAdapter
import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.core.utils.adapters.WordTranslationDeserializer
import ru.mareanexx.core.utils.tracker.NetworkMonitor
import ru.mareanexx.core.utils.tracker.NetworkMonitorImpl
import ru.mareanexx.feature_translate.data.remote.api.TranslateApi
import java.time.LocalDate
import java.time.OffsetDateTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttp)
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(WordTranslation::class.java, WordTranslationDeserializer())
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeAdapter())
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        loggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor = NetworkMonitorImpl(context)

    @Singleton
    @Provides
    fun provideTranslateApi(retrofit: Retrofit) = retrofit.create(TranslateApi::class.java)
}