package com.shaadi.peopleinteractive.network

inline fun <reified T> BaseResponse<T>.mapToResult(): T {
    return if (T::class == Unit::class) {
        @Suppress("UNCHECKED_CAST")
        Unit as T
    } else results ?: throw Exception("Something went wrong.")
}
