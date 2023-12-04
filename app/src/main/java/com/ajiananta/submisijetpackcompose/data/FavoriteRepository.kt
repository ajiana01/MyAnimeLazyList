package com.ajiananta.submisijetpackcompose.data

import com.ajiananta.submisijetpackcompose.model.AnimeList
import com.ajiananta.submisijetpackcompose.model.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FavoriteRepository {
    private val dataAnime = mutableListOf<Favorite>()

    init {
        if (dataAnime.isEmpty()) {
            AnimeList.dummyDataAnime.forEach {
                dataAnime.add(Favorite(it, false))
            }
        }
    }

    fun getAllFavorite(): Flow<List<Favorite>> {
        return flowOf(dataAnime)
    }

    fun getFavoriteById(animeId: Long): Favorite {
        return dataAnime.first {
            it.anime.id == animeId
        }
    }

    fun updateFavorite(animeId: Long, isFav: Boolean): Flow<Boolean> {
        val index = dataAnime.indexOfFirst { it.anime.id == animeId }
        val result = if (index >= 0) {
            val favorite = dataAnime[index]
            dataAnime[index] =
                favorite.copy(anime = favorite.anime, favorite = isFav)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedFavorite(): Flow<List<Favorite>> {
        return getAllFavorite()
            .map {
                    it.filter {fav ->
                        fav.favorite
                    }
            }
    }

    fun searchFavorite(query: String) = flow {
        val data = dataAnime.filter {
            it.anime.title.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    companion object {
        @Volatile
        private var instance: FavoriteRepository? = null

        fun getInstance(): FavoriteRepository =
            instance ?: synchronized(this) {
                FavoriteRepository().apply {
                    instance = this
                }
            }
    }
}