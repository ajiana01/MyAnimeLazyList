package com.ajiananta.submisijetpackcompose.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajiananta.submisijetpackcompose.data.FavoriteRepository
import com.ajiananta.submisijetpackcompose.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UIState<StateFav>> = MutableStateFlow(UIState.Loading)
    val uiState: MutableStateFlow<UIState<StateFav>>
        get() = _uiState

    fun getFavorite() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            repository.getAddedFavorite()
                .collect { favorite ->
                    _uiState.value = UIState.Success(StateFav(favorite))
                }
        }
    }

    fun updateFavorite(id: Long, fav: Boolean) {
        viewModelScope.launch {
            val isUpdated = repository.updateFavorite(id, fav).first()
            if (isUpdated) {
                val updatedFavorites = repository.getAddedFavorite().first()
                _uiState.value = UIState.Success(StateFav(updatedFavorites))
            }
            /*repository.updateFavorite(id, fav)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getFavorite()
                    }
                }*/
        }
    }
}