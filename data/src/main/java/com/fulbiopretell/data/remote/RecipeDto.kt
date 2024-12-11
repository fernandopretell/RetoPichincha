package com.fulbiopretell.data.remote

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("description") val shortDescription: String,
    val isFavorite: Boolean = false
)
