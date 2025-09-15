package ru.mareanexx.feature_translate.domain.entity

import ru.mareanexx.core.utils.common.Translation
import java.time.LocalDate

data class TranslationHistoryItem(
    override val id: Long,
    override val original: String,
    override val translation: String,
    val date: LocalDate
) : Translation