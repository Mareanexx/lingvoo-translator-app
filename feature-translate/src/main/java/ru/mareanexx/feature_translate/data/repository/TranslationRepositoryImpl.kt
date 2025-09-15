package ru.mareanexx.feature_translate.data.repository

import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.core.utils.common.Error
import ru.mareanexx.core.utils.common.ErrorType
import ru.mareanexx.core.utils.common.resolveErrorType
import ru.mareanexx.core.utils.tracker.NetworkMonitor
import ru.mareanexx.feature_translate.data.remote.api.TranslateApi
import ru.mareanexx.feature_translate.data.remote.dto.TranslateWordRequest
import ru.mareanexx.feature_translate.domain.repository.TranslationRepository
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val translateApi: TranslateApi,
    private val networkMonitor: NetworkMonitor
): TranslationRepository {
    override suspend fun getTranslatedWord(word: TranslateWordRequest): BaseResult<WordTranslation, Error> {
        if (!networkMonitor.isConnected()) {
            return BaseResult.Error(Error(
                type = ErrorType.NoInternetConnection
            ))
        }

        val result = translateApi.getTranslation(word.text)

        return if (result.isSuccessful) {
            val data = result.body()!!
            BaseResult.Success(data)
        } else {
            BaseResult.Error(Error(
                message = result.message(),
                type = resolveErrorType(result.code())
            ))
        }
    }
}