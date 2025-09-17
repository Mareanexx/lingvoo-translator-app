package ru.mareanexx.core.utils.common

data class Error(
    val message: String? = null,
    val type: ErrorType
)

enum class ErrorType {
    NoInternetConnection,
    ServerError,
    ClientError,
    UnknownError,
    NoTranslation
}

fun resolveErrorType(code: Int) = when {
    code < 500 -> ErrorType.ClientError
    else -> ErrorType.ServerError
}