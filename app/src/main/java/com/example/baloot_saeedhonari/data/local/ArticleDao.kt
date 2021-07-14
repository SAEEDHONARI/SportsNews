package com.example.baloot_saeedhonari.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.baloot_saeedhonari.data.model.Article

@Dao
interface ArticleDao {


    /**
     * Insert articles into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<Article>): List<Long>

    /**
     * Get all the articles from database
     */
    @Query("SELECT * FROM article_table")
    fun getNewsArticles(): LiveData<List<Article>>

    @Query("DELETE FROM article_table")
    abstract fun deleteAllArticles()
}