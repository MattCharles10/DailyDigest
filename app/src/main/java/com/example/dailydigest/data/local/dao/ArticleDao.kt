package com.example.dailydigest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dailydigest.data.local.entities.SavedArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM saved_articles ORDER BY savedAt DESC")
    fun getSavedArticles(): Flow<List<SavedArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: SavedArticleEntity)

    @Query("DELETE FROM saved_articles WHERE id = :articleId")
    suspend fun deleteArticle(articleId: String)

    @Query("SELECT * FROM saved_articles WHERE id = :articleId")
    suspend fun getArticleById(articleId: String): SavedArticleEntity?
}