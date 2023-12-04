package com.ajiananta.submisijetpackcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajiananta.submisijetpackcompose.data.FavoriteRepository
import com.ajiananta.submisijetpackcompose.ui.screen.detail.DetailAnimeViewModel
import com.ajiananta.submisijetpackcompose.ui.screen.favorite.FavoriteViewModel
import com.ajiananta.submisijetpackcompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: FavoriteRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailAnimeViewModel::class.java)) {
            return DetailAnimeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}