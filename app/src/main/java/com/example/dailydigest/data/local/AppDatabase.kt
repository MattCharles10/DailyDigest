package com.example.dailydigest.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.dailydigest.data.local.dao.ArticleDao
import com.example.dailydigest.data.local.entities.SavedArticleEntity

@Database(
    entities = [SavedArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "daily_digest_db"
                ).build().also { Instance = it }
            }
        }
    }
}