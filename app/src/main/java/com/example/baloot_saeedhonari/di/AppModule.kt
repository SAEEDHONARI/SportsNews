package com.example.baloot_saeedhonari.di

import android.content.Context
import androidx.room.Room
import com.example.baloot_saeedhonari.data.local.ArticleDao
import com.example.baloot_saeedhonari.data.local.ArticleDatabase
import com.example.baloot_saeedhonari.data.remote.ArticleApi
import com.example.baloot_saeedhonari.data.remote.network.LiveDataCallAdapterFactoryForRetrofit
import com.example.baloot_saeedhonari.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Waheed on 04,November,2019
 * Migrated to Hilt 20, June, 2021
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideNewsService(): ArticleApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(ArticleApi::class.java)
    }


    /**
     * Provides app AppDatabase
     */
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(context, ArticleDatabase::class.java, "news-db")
            .fallbackToDestructiveMigration().build()


    /**
     * Provides NewsArticlesDao an object to access NewsArticles table from Database
     */
    @Singleton
    @Provides
    fun provideUserDao(db: ArticleDatabase): ArticleDao = db.ArticlesDao()


}
