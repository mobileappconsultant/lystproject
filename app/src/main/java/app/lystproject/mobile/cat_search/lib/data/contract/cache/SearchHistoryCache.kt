package app.lystproject.mobile.cat_search.lib.data.contract.cache

import app.lystproject.mobile.cat_search.model.CatModel

internal interface SearchHistoryCache {
    suspend fun saveSearch(cat: CatModel)
    suspend fun getSearchHistory(): List<CatModel>
    suspend fun clearSearchHistory()
}
