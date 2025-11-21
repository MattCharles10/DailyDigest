package com.example.dailydigest.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dailydigest.presentation.components.ArticleCard
import com.example.dailydigest.presentation.components.CategoryChip
import com.example.dailydigest.presentation.components.SearchBar
import com.example.dailydigest.viewmodel.NewsViewModel

@Composable
fun NewsFeedScreen(
    viewModel: NewsViewModel,
    navController: NavController
) {
    val articles by viewModel.articles.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState() // Add this line

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar - Add this section
            SearchBar(
                query = searchQuery, // This should work now
                onQueryChange = { viewModel.onSearchQueryChange(it) },
                onSearch = { viewModel.performSearch() }
            )

            // Categories Row
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                Text(
                    text = "Categories",
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )

                androidx.compose.foundation.layout.Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    listOf("general", "technology", "sports", "business", "entertainment", "health").forEach { category ->
                        CategoryChip(
                            category = category,
                            isSelected = selectedCategory == category,
                            onCategorySelected = { viewModel.loadHeadlines(category) }
                        )
                    }
                }
            }

            // News List
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (articles.isEmpty()) {
                Text(
                    text = "No articles found",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(articles) { article ->
                        ArticleCard(
                            article = article,
                            onArticleClick = { url ->
                                // TODO: Open article in browser or detail screen
                            },
                            onBookmarkClick = { articleToSave ->
                                viewModel.toggleSaveArticle(articleToSave)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}