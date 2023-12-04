package com.ajiananta.submisijetpackcompose.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Anime (
    val id: Long,
    @DrawableRes val image: Int,
    val title: String,
    @StringRes val type: Int,
    @StringRes val date: Int,
    @StringRes val member: Int,
    @StringRes val synopsis: Int,
)