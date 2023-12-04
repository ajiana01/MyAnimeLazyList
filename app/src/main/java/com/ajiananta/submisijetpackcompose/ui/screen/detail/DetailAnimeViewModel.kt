package com.ajiananta.submisijetpackcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajiananta.submisijetpackcompose.data.FavoriteRepository
import com.ajiananta.submisijetpackcompose.model.Anime
import com.ajiananta.submisijetpackcompose.model.Favorite
import com.ajiananta.submisijetpackcompose.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailAnimeViewModel (private val repository: FavoriteRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UIState<Favorite>> = MutableStateFlow(UIState.Loading)
    val uiState: MutableStateFlow<UIState<Favorite>>
        get() = _uiState

    fun getFavoriteById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            _uiState.value = UIState.Success(repository.getFavoriteById(id))
        }
    }

    fun addToFavorite(anime: Anime, fav: Boolean) {
        viewModelScope.launch {
            repository.updateFavorite(anime.id, fav)
        }
    }
}