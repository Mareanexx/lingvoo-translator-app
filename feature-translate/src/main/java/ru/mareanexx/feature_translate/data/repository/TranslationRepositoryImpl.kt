package ru.mareanexx.feature_translate.data.repository

import android.util.Log
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.core.utils.common.Error
import ru.mareanexx.core.utils.common.ErrorType
import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.core.utils.common.resolveErrorType
import ru.mareanexx.core.utils.network.tracker.NetworkMonitor
import ru.mareanexx.feature_translate.data.remote.api.TranslationApi
import ru.mareanexx.feature_translate.data.remote.dto.TranslateWordRequest
import ru.mareanexx.feature_translate.domain.repository.TranslationRepository
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val translationApi: TranslationApi,
    private val networkMonitor: NetworkMonitor
): TranslationRepository {
    override suspend fun getTranslatedWord(word: TranslateWordRequest): BaseResult<WordTranslation, Error> {
        if (!networkMonitor.isConnected()) {
            return BaseResult.Error(Error(
                type = ErrorType.NoInternetConnection
            ))
        }
        Log.d("TRANSLATION", "Internet connection checked")

        val result = translationApi.getTranslation(word.text)

        Log.d("TRANSLATION", "API request was done = ${result.body()?.translation}")
        Log.d("TRANSLATION", "Response Code is ${result.code()}")
        return if (result.isSuccessful) {
            val data = result.body()

            if (data == null) BaseResult.Error(Error(type = ErrorType.NoTranslation))
            else BaseResult.Success(data)
        } else {
            BaseResult.Error(Error(
                message = result.message(),
                type = resolveErrorType(result.code())
            ))
        }
    }
}