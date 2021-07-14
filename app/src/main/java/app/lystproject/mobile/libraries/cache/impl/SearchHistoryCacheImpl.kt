package app.lystproject.mobile.libraries.cache.impl

import app.lystproject.mobile.cat_search.lib.data.contract.cache.SearchHistoryCache
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.libraries.cache.mapper.CacheModelMapper
import app.lystproject.mobile.libraries.cache.mapper.CatModelMapper
import app.lystproject.mobile.libraries.cache.model.CatCacheModel
import app.lystproject.mobile.libraries.cache.room.CatDetailDao
import app.lystproject.mobile.libraries.cache.room.SearchHistoryDao
import javax.inject.Inject

internal class SearchHistoryCacheImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
    private val catDetailDao: CatDetailDao,
    private val catCacheModelMapper: CatModelMapper
) : SearchHistoryCache {

    override suspend fun saveSearch(cat: CatModel) {
        val catCacheModel = catCacheModelMapper.mapToModel(cat)
        catCacheModel.lastUpdated = System.currentTimeMillis()
        searchHistoryDao.insertSearch(catCacheModel)
    }

    override suspend fun getSearchHistory(): List<CatModel> {
        val catModels: List<CatCacheModel> = searchHistoryDao.recentSearches()
        return catModels.map(catCacheModelMapper::mapToEntity)
    }

    override suspend fun clearSearchHistory() {
        searchHistoryDao.clearHistory()
        catDetailDao.clearData()
    }
}
