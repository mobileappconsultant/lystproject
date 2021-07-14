package app.lystproject.mobile.cat_search.lib.data.repository

import app.lystproject.mobile.cat_search.lib.data.contract.cache.SearchHistoryCache
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchHistoryRepository
import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class SearchHistoryRepositoryImpl @Inject constructor(
    private val searchHistoryCache: SearchHistoryCache,
) : SearchHistoryRepository {

    override suspend fun saveSearch(cat: CatModel) {
        searchHistoryCache.saveSearch(cat)
    }

    override fun getSearchHistory(): Flow<List<CatModel>> {
        return flow {
            emit(searchHistoryCache.getSearchHistory())
        }
    }

    override suspend fun clearSearchHistory() {
        searchHistoryCache.clearSearchHistory()
    }
}
