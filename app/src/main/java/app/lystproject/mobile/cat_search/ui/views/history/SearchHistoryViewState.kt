package app.lystproject.mobile.cat_search.ui.views.history

import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.presentation.base.ViewState


data class SearchHistoryViewState(
    val history: List<CatModel> = emptyList(),
    val showHistory: Boolean = false,
    val showRecentSearchGroup: Boolean = false,
    val showHistoryPrompt: Boolean = false
) : ViewState
