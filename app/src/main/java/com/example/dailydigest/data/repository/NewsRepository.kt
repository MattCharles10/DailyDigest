package com.example.dailydigest.data.repository

import com.example.dailydigest.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(category: String? = null): List<Article>
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticle(articleId: String)
}