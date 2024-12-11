package com.fulbiopretell.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fulbiopretell.data.local.RecipeDao
import com.fulbiopretell.data.local.entity.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
