package app.lystproject.mobile.cat_search.presentation.viewstate

import app.lystproject.mobile.cat_search.ui.views.history.SearchHistoryViewState
import app.lystproject.mobile.cat_search.ui.views.history.SearchHistoryViewStateFactory
import app.lystproject.mobile.cat_search.ui.views.result.SearchResultViewState
import app.lystproject.mobile.cat_search.ui.views.result.SearchResultViewStateFactory
import app.lystproject.presentation.base.ScreenState


data class SearchScreenState(
    val searchHistoryState: SearchHistoryViewState = SearchHistoryViewStateFactory.initialState,
    val searchResultState: SearchResultViewState = SearchResultViewStateFactory.initialState
) : ScreenState
