package app.lystproject.mobile.libraries.cache.di

import android.content.Context
import app.lystproject.mobile.cat_search.lib.data.contract.cache.CatDetailCache
import app.lystproject.mobile.cat_search.lib.data.contract.cache.SearchHistoryCache
import app.lystproject.mobile.libraries.cache.impl.CatDetailCacheImpl
import app.lystproject.mobile.libraries.cache.impl.SearchHistoryCacheImpl
import app.lystproject.mobile.libraries.cache.room.CatDetailDao
import app.lystproject.mobile.libraries.cache.room.SearchHistoryDao
import app.lystproject.mobile.libraries.cache.room.TroubleCatsDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface CacheModule {

    @get:Binds
    val SearchHistoryCacheImpl.searchHistoryCache: SearchHistoryCache

    @get:Binds
    val CatDetailCacheImpl.catDetailCache: CatDetailCache

}
