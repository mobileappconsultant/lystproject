package app.lystproject.mobile.libraries.cache.di

import android.content.Context
import app.lystproject.mobile.libraries.cache.room.CatDetailDao
import app.lystproject.mobile.libraries.cache.room.SearchHistoryDao
import app.lystproject.mobile.libraries.cache.room.TroubleCatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object CacheDatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): TroubleCatsDatabase {
        return TroubleCatsDatabase.build(context)
    }

    @[Provides Singleton]
    fun provideSearchHistoryDao(starWarsDatabase: TroubleCatsDatabase): SearchHistoryDao {
        return starWarsDatabase.searchHistoryDao
    }

    @[Provides Singleton]
    fun provideCatDetailDao(starWarsDatabase: TroubleCatsDatabase): CatDetailDao {
        return starWarsDatabase.catDetailDao
    }
}