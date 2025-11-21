package com.example.dailydigest.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailydigest.R
import com.example.dailydigest.presentation.screens.NewsFeedScreen
import com.example.dailydigest.presentation.screens.SavedArticlesScreen
import com.example.dailydigest.viewmodel.NewsViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.NewsFeed.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.NewsFeed.route) {
                // Option 1: Use real NewsFeedScreen with ViewModel
                val viewModel: NewsViewModel = hiltViewModel()
                NewsFeedScreen(
                    viewModel = viewModel,
                    navController = navController
                )

                // Option 2: Keep test screen (comment out above and uncomment below)
                /*
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "📰 ${stringResource(id = Screen.NewsFeed.titleResId)}",
                        color = Color.Blue,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Navigation is working! ✅")
                    Text("Tap Saved Articles below to test navigation")
                }
                */
            }
            composable(Screen.SavedArticles.route) {
                // Option 1: Use real SavedArticlesScreen with ViewModel
                val viewModel: NewsViewModel = hiltViewModel()
                SavedArticlesScreen(
                    viewModel = viewModel,
                    navController = navController
                )

                // Option 2: Keep test screen (comment out above and uncomment below)
                /*
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🔖 ${stringResource(id = Screen.SavedArticles.titleResId)}",
                        color = Color.Green,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Navigation is working! ✅")
                    Text("Tap News Feed below to go back")
                }
                */
            }
        }
    }
}

// Updated Screen class with titleResId
sealed class Screen(val route: String, val titleResId: Int) {
    object NewsFeed : Screen("news_feed", R.string.news_feed)
    object SavedArticles : Screen("saved_articles", R.string.saved_articles)
}