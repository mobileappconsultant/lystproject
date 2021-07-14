package app.lystproject.mobile.cat_search.presentation

import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.presentation.base.ViewIntent

sealed interface SearchScreenIntent : ViewIntent
data class RetrySearchIntent(val query: String) : SearchScreenIntent
data class SaveSearchIntent(val cat: CatModel) : SearchScreenIntent
data class SearchIntent(val query: String) : SearchScreenIntent
object ClearSearchHistoryIntent : SearchScreenIntent
data class UpdateHistoryIntent(val cat: CatModel) : SearchScreenIntent
object LoadSearchHistory : SearchScreenIntent