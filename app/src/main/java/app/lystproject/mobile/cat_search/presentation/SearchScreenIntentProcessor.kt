package app.lystproject.mobile.cat_search.presentation

import app.lystproject.mobile.cat_search.lib.domain.usecase.search.SearchCats
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.presentation.SearchScreenResult.SearchCatResult
import app.lystproject.presentation.base.InvalidViewIntentException
import app.lystproject.presentation.base.ViewIntent
import app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory.ClearSearchHistory
import app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory.GetSearchHistory
import app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory.SaveSearch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchScreenIntentProcessor @Inject constructor(
    private val searchCats: SearchCats,
    private val saveSearch: SaveSearch,
    private val getSearchHistory: GetSearchHistory,
    private val clearSearchHistory: ClearSearchHistory,
) : SearchIntentProcessor {

    override fun intentToResult(viewIntent: ViewIntent): Flow<SearchScreenResult> {
        return when (viewIntent) {
            is SearchIntent -> executeSearch(viewIntent.query)
            is SaveSearchIntent -> cacheCat(viewIntent.cat)
            is UpdateHistoryIntent -> updateCache(viewIntent.cat)
            LoadSearchHistory -> loadSearchHistory()
            ClearSearchHistoryIntent -> clearCache()
            is RetrySearchIntent -> executeSearch(viewIntent.query)
            else -> throw InvalidViewIntentException(viewIntent)
        }
    }

    private fun clearCache(): Flow<SearchScreenResult.LoadedHistory> {
        return flow {
            clearSearchHistory()
            emit(SearchScreenResult.LoadedHistory(emptyList()))
        }.catch { error ->
            error.printStackTrace()
        }
    }

    private fun cacheCat(cat: CatModel): Flow<SearchScreenResult> {
        return flow<SearchScreenResult> {
            saveSearch(cat)
        }.catch { error ->
            error.printStackTrace()
        }
    }

    private fun updateCache(cat: CatModel): Flow<SearchScreenResult> {
        return flow {
            saveSearch(cat)
            emitAll(loadSearchHistory())
        }.catch { error ->
            error.printStackTrace()
        }
    }

    private fun loadSearchHistory(): Flow<SearchScreenResult> {
        return getSearchHistory()
            .map { cats ->
                SearchScreenResult.LoadedHistory(cats)
            }.catch { error ->
                error.printStackTrace()
            }
    }

    private fun executeSearch(query: String): Flow<SearchScreenResult> {
        if (query.isBlank()) {
            return loadSearchHistory()
        }
        return searchCats(query.trim())
            .map<List<CatModel>, SearchCatResult> { cats ->
                SearchCatResult.Success(cats)
            }.onStart {
                emit(SearchCatResult.Searching)
            }.catch { throwable ->
                emit(SearchCatResult.Error(throwable))
            }
    }
}
