package ru.mareanexx.core.utils.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.mareanexx.core.utils.common.WordTranslation
import java.lang.reflect.Type

class WordTranslationDeserializer : JsonDeserializer<WordTranslation?> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): WordTranslation? {
        if (json == null || !json.isJsonArray) return null

        val jsonArray = json.asJsonArray
        if (jsonArray.size() == 0) return null

        val firstObj = jsonArray[0].asJsonObject

        val id = firstObj.get("id")?.asLong ?: -1
        val text = firstObj.get("text")?.asString ?: ""

        val meaningsArray = firstObj.getAsJsonArray("meanings")
        if (meaningsArray == null || meaningsArray.size() == 0) {
            return WordTranslation(id, text, "")
        }

        val firstMeaning = meaningsArray[0].asJsonObject
        val translation = firstMeaning.getAsJsonObject("translation")?.get("text")?.asString ?: ""

        return WordTranslation(id, text, translation)
    }
}