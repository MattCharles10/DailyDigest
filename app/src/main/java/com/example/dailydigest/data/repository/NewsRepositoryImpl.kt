package com.example.dailydigest.data.repository

import com.example.dailydigest.data.local.AppDatabase
import com.example.dailydigest.data.remote.api.RetrofitInstance
import com.example.dailydigest.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : NewsRepository {

    private val newsApi = RetrofitInstance.newsApi

    override suspend fun getTopHeadlines(category: String?): List<Article> {
        return try {
            val response = newsApi.getTopHeadlines(category = category)
            if (response.status == "ok") {
                response.articles.map { it.toArticle(category ?: "General") }
            } else {
                getSampleArticles() // Fallback if API returns error
            }
        } catch (e: Exception) {
            e.printStackTrace()
            getSampleArticles() // Fallback if network fails
        }
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return database.articleDao().getSavedArticles().map { entities ->
            entities.map { it.toArticle() }
        }
    }

    override suspend fun saveArticle(article: Article) {
        database.articleDao().saveArticle(article.toEntity())
    }

    override suspend fun deleteArticle(articleId: String) {
        database.articleDao().deleteArticle(articleId)
    }

    private fun com.example.dailydigest.data.local.entities.SavedArticleEntity.toArticle(): Article {
        return Article(
            id = id,
            title = title,
            description = description ?: "No description",
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt ?: "Unknown date",
            source = source ?: "Unknown Source",
            category = category,
            isSaved = true
        )
    }

    private fun Article.toEntity(): com.example.dailydigest.data.local.entities.SavedArticleEntity {
        return com.example.dailydigest.data.local.entities.SavedArticleEntity(
            id = id ?: url.hashCode().toString(),
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            source = source,
            category = category
        )
    }

    // Fallback sample data
    private fun getSampleArticles(): List<Article> {
        return listOf(
            Article(
                title = "Get API Key from NewsAPI",
                description = "Visit newsapi.org to get your free API key and see real news here!",
                url = "https://newsapi.org",
                urlToImage = null,
                publishedAt = "2024-01-01",
                source = "NewsAPI",
                category = "general",
                isSaved = false
            ),
            Article(
                title = "How to Use This App",
                description = "Get your API key from newsapi.org and replace 'YOUR_API_KEY_HERE' in NewsApi.kt",
                url = "https://example.com",
                urlToImage = null,
                publishedAt = "2024-01-01",
                source = "Daily Digest",
                category = "technology",
                isSaved = false
            )
        )
    }
}