package com.fulbiopretell.retopichincha.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.fulbiopretell.retopichincha.viewmodels.HomeViewModel
import com.fulbiopretell.retopichincha.viewmodels.OnboardingViewModel
import com.fulbiopretell.retopichincha.viewmodels.RecipeDetailViewModel
import com.fulbiopretell.retopichincha.viewmodels.SplashViewModel
import com.fulbiopretell.retopichincha.ui.detail.RecipeDetailScreen
import com.fulbiopretell.retopichincha.ui.home.HomeScreen
import com.fulbiopretell.retopichincha.ui.onboarding.OnboardingScreen
import com.fulbiopretell.retopichincha.ui.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    val splashViewModel: SplashViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen()
            LaunchedEffect(splashViewModel.navigateToOnboarding, splashViewModel.navigateToHome) {
                when {
                    splashViewModel.navigateToOnboarding -> {
                        navController.navigate(Routes.ONBOARDING) {
                            popUpTo(Routes.SPLASH) { inclusive = true }
                        }
                    }
                    splashViewModel.navigateToHome -> {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.SPLASH) { inclusive = true }
                        }
                    }
                }
            }
        }

        composable(Routes.ONBOARDING) {
            val onboardingViewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen {
                onboardingViewModel.completeOnboarding()
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.ONBOARDING) { inclusive = true }
                }
            }
        }

        composable(Routes.HOME) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                homeViewModel = homeViewModel,
                onRecipeClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) {
            val detailViewModel: RecipeDetailViewModel = hiltViewModel()
            RecipeDetailScreen(viewModel = detailViewModel)
        }
    }
}
