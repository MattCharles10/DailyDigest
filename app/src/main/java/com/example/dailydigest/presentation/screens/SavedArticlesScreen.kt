package com.example.dailydigest.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.dailydigest.viewmodel.NewsViewModel

@Composable
fun SavedArticlesScreen(
    viewModel: NewsViewModel,
    navController: NavController
) {
    val savedArticles by viewModel.savedArticles.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (savedArticles.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No saved articles yet",
                        modifier = Modifier.padding(16.dp)
                    )
                    Text("Save articles from News Feed to see them here!")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
                ) {
                    items(
                        items = savedArticles,
                        key = { article -> article.id ?: article.url }
                    ) { article ->
                        ArticleCard(
                            article = article,
                            onArticleClick = { url ->
                                // TODO: Open article in browser
                            },
                            onBookmarkClick = { articleToSave ->
                                viewModel.toggleSaveArticle(articleToSave)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}