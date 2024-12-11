package com.fulbiopretell.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("recipes")
    suspend fun getRecipes(): List<RecipeDto>
}
