package ru.mareanexx.feature_translate.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mareanexx.core.utils.common.WordTranslation

interface TranslationApi {
    @GET("search")
    suspend fun getTranslation(@Query("search") search: String): Response<WordTranslation>
}