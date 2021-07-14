package app.lystproject.mobile.cat_search.lib.domain.repository

import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    suspend fun saveSearch(cat: CatModel)
    fun getSearchHistory(): Flow<List<CatModel>>
    suspend fun clearSearchHistory()
}
