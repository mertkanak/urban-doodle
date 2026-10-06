package com.example.grokapp.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object LOGIN

    @Serializable
    data class HOME(val isim: String)
}