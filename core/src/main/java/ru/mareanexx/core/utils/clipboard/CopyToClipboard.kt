package ru.mareanexx.core.utils.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import ru.mareanexx.core.R

fun Context.copyToClipboard(text: String): Boolean {
    return try {
        val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return false
        val clip = ClipData.newPlainText(getString(R.string.clipboard_label), text)
        clipboard.setPrimaryClip(clip)
        true
    } catch (e: Exception) {
        false
    }
}