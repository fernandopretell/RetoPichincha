package com.fulbiopretell.domain

data class Recipe(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val shortDescription: String,
    val isFavorite: Boolean = false
)
