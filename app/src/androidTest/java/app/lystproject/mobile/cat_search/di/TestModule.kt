package app.lystproject.mobile.cat_search.di

import app.lystproject.mobile.cat_search.di.fakes.FakeCatDetailRepository
import app.lystproject.mobile.cat_search.di.fakes.FakeSearchHistoryRepository
import app.lystproject.mobile.cat_search.di.fakes.FakeSearchRepository
import app.lystproject.mobile.cat_search.di.fakes.TestPostExecutionThread
import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import app.lystproject.mobile.cat_search.lib.domain.repository.CatDetailRepository
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchHistoryRepository
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TestModule {

    @Provides
    fun searchHistoryRepository(): SearchHistoryRepository = FakeSearchHistoryRepository()

    @Provides
    fun catDetailRepository(): CatDetailRepository = FakeCatDetailRepository()

    @Provides
    fun searchRepository(): SearchRepository = FakeSearchRepository()

    @Provides
    fun executionThread(): PostExecutionThread = TestPostExecutionThread()
}
