package com.fulbiopretell.retopichincha.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fulbiopretell.retopichincha.viewmodels.HomeViewModel
import com.fulbiopretell.retopichincha.ui.components.FavoriteRecipeItem
import com.fulbiopretell.retopichincha.ui.components.RecipeItemCard

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onRecipeClick: (Int) -> Unit
) {
    val allRecipes by homeViewModel.allRecipes.collectAsState()
    val favoriteRecipes by homeViewModel.favoriteRecipes.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.refreshRecipes()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Todas las Recetas",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().weight(3f)
        ) {
            items(allRecipes) { r ->
                RecipeItemCard(
                    recipe = r,
                    onFavoriteToggle = { homeViewModel.toggleFavorite(r.id) },
                    onClick = { onRecipeClick(r.id) }
                )
            }
        }

        Text(
            text = "Favoritas",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            items(favoriteRecipes) { r ->
                FavoriteRecipeItem(
                    recipe = r,
                    onFavoriteToggle = { homeViewModel.toggleFavorite(r.id) },
                    onClick = { onRecipeClick(r.id) }
                )
            }
        }
    }
}



