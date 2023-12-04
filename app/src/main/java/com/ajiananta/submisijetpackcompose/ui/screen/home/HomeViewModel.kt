package com.ajiananta.submisijetpackcompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajiananta.submisijetpackcompose.data.FavoriteRepository
import com.ajiananta.submisijetpackcompose.model.Favorite
import com.ajiananta.submisijetpackcompose.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel ( private val repository: FavoriteRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<Favorite>>> = MutableStateFlow(UIState.Loading)
    val uiState: MutableStateFlow<UIState<List<Favorite>>>
        get() = _uiState
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllFavorites() {
        viewModelScope.launch {
            repository.getAllFavorite()
                .catch {
                    _uiState.value = UIState.Error(it.message.toString())
                }
                .collect { favorites ->
                    _uiState.value = UIState.Success(favorites)
                }
        }
    }

    fun searchAnime(newQuery: String) {
        _query.value = newQuery
        _uiState.value = UIState.Loading
        viewModelScope.launch {
            repository.searchFavorite(newQuery)
                .catch {
                    _uiState.value = UIState.Error(it.message.toString()) }
                .collect { favorites ->
                    _uiState.value = UIState.Success(favorites)
                }
        }
    }

}