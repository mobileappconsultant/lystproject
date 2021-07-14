package app.lystproject.mobile.cat_search.lib.data.di

import app.lystproject.mobile.cat_search.lib.data.repository.CatDetailRepositoryImpl
import app.lystproject.mobile.cat_search.lib.data.repository.SearchHistoryRepositoryImpl
import app.lystproject.mobile.cat_search.lib.data.repository.SearchRepositoryImpl
import app.lystproject.mobile.cat_search.lib.domain.repository.CatDetailRepository
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchHistoryRepository
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface DataModule {

    @get:Binds
    val SearchRepositoryImpl.searchRepository: SearchRepository

    @get:Binds
    val CatDetailRepositoryImpl.catDetailRepository: CatDetailRepository

    @get:Binds
    val SearchHistoryRepositoryImpl.searchHistoryRepository: SearchHistoryRepository
}