package com.example.baloot_saeedhonari.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baloot_saeedhonari.data.local.ArticleDao
import com.example.baloot_saeedhonari.data.model.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {

    /**
     * Get DAO's
     */
    abstract fun ArticlesDao(): ArticleDao

}