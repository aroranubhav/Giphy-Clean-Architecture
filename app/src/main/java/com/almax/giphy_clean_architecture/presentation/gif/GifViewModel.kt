package com.almax.giphy_clean_architecture.presentation.gif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almax.giphy_clean_architecture.domain.model.GifData
import com.almax.giphy_clean_architecture.domain.usecase.GetTrendingGifsUseCase
import com.almax.giphy_clean_architecture.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val useCase: GetTrendingGifsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<GifData>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<GifData>>>
        get() = _uiState

    init {
        getTrendingGifs()
    }

    private fun getTrendingGifs() {
        viewModelScope.launch {
            useCase()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message.toString())
                }
                .collect { gifs ->
                    _uiState.value = UiState.Success(gifs)
                }
        }
    }
}