package com.fulbiopretell.data.extensions

import com.fulbiopretell.data.local.entity.RecipeEntity
import com.fulbiopretell.data.remote.RecipeDto
import com.fulbiopretell.domain.Recipe

fun RecipeDto.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        shortDescription = this.shortDescription,
        isFavorite = this.isFavorite
    )
}

fun Recipe.toDto(): RecipeDto {
    return RecipeDto(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        shortDescription = this.shortDescription,
        isFavorite = this.isFavorite
    )
}

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        shortDescription = this.shortDescription,
        isFavorite = this.isFavorite
    )
}

fun RecipeEntity.fromDomain(recipe: Recipe): RecipeEntity {
    return RecipeEntity(
        id = recipe.id,
        name = recipe.name,
        imageUrl = recipe.imageUrl,
        shortDescription = recipe.shortDescription,
        isFavorite = recipe.isFavorite
    )
}
