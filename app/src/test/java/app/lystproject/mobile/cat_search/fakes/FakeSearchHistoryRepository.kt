package app.lystproject.mobile.cat_search.fakes

import app.lystproject.mobile.cat_search.lib.domain.repository.SearchHistoryRepository
import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSearchHistoryRepository : SearchHistoryRepository {

    private val cache = LinkedHashMap<String, CatModel>()

    override suspend fun saveSearch(cat: CatModel) {
        cache[cat.id] = cat
    }

    override fun getSearchHistory(): Flow<List<CatModel>> {
        return flowOf(cache.values.toList().reversed())
    }

    override suspend fun clearSearchHistory() {
        cache.clear()
    }
}
