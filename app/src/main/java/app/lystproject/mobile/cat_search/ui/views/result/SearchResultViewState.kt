package app.lystproject.mobile.cat_search.ui.views.result

import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.presentation.base.ViewState

data class SearchResultViewState(
    val searchResult: List<CatModel> = emptyList(),
    val showProgress: Boolean = false,
    val showEmpty: Boolean = false,
    val showResult: Boolean = false,
    val showError: Boolean = false,
    val error: String? = null
) : ViewState
