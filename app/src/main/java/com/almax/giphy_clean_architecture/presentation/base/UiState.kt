package com.almax.giphy_clean_architecture.presentation.base

sealed interface UiState<out T> {

    data class Success<T>(val data: T) : UiState<T>

    data class Error(val error: String) : UiState<Nothing>

    data object Loading : UiState<Nothing>
}