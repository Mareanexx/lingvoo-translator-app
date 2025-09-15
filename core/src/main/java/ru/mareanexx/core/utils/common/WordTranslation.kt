package ru.mareanexx.core.utils.common

import com.google.gson.annotations.SerializedName

data class WordTranslation(
    override val id: Long,
    @SerializedName("text")
    override val original: String,
    override val translation: String,
): Translation