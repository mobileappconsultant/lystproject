package app.lystproject.mobile.cat_search.presentation

import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.presentation.base.ViewResult

sealed class SearchScreenResult : ViewResult {
    data class LoadedHistory(val searchHistory: List<CatModel>) : SearchScreenResult()
    sealed class SearchCatResult : SearchScreenResult() {
        object Searching : SearchCatResult()
        data class Error(val throwable: Throwable) : SearchCatResult()
        data class Success(val cats: List<CatModel>) : SearchCatResult()
    }
}
