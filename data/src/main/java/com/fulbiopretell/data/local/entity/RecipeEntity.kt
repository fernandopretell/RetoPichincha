package com.fulbiopretell.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fulbiopretell.domain.Recipe

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey()
    val id: Int,
    val name: String,
    val imageUrl: String,
    val shortDescription: String,
    val isFavorite: Boolean
)
