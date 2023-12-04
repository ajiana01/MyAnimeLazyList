package com.ajiananta.submisijetpackcompose.di

import com.ajiananta.submisijetpackcompose.data.FavoriteRepository

object DataInjection {
    fun provideRepository(): FavoriteRepository {
        return FavoriteRepository.getInstance()
    }
}