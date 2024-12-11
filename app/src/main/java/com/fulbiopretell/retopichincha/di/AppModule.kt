package com.fulbiopretell.retopichincha.di

import com.fulbiopretell.domain.GetFavoriteRecipesUseCase
import com.fulbiopretell.domain.GetRecipeDetailUseCase
import com.fulbiopretell.domain.GetRecipesUseCase
import com.fulbiopretell.domain.RecipeRepository
import com.fulbiopretell.domain.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGetRecipesUseCase(repo: RecipeRepository) = GetRecipesUseCase(repo)

    @Provides
    fun provideGetRecipeDetailUseCase(repo: RecipeRepository) = GetRecipeDetailUseCase(repo)

    @Provides
    fun provideToggleFavoriteUseCase(repo: RecipeRepository) = ToggleFavoriteUseCase(repo)

    @Provides
    fun provideGetFavoriteRecipesUseCase(repo: RecipeRepository) = GetFavoriteRecipesUseCase(repo)
}
