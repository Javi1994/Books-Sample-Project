package com.javi.common

sealed class Resource<T>(
    val data: T? = null,
    val error: Exception? = null,
    val isLoading: Boolean = false,
) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(error: Exception?) : Resource<T>(error = error)
    class Loading<T>(isLoading: Boolean = true) : Resource<T>(isLoading = isLoading)
}

