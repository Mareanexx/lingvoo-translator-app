package ru.mareanexx.feature_translate.domain.repository

import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.core.utils.common.Error
import ru.mareanexx.feature_translate.data.remote.dto.TranslateWordRequest

interface TranslationRepository {
    suspend fun getTranslatedWord(word: TranslateWordRequest): BaseResult<WordTranslation, Error>
}