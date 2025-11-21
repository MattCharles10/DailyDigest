package com.example.dailydigest.di

import android.content.Context
import com.example.dailydigest.data.local.AppDatabase
import com.example.dailydigest.data.repository.NewsRepository
import com.example.dailydigest.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(database: AppDatabase): NewsRepository {
        return NewsRepositoryImpl(database)
    }
}