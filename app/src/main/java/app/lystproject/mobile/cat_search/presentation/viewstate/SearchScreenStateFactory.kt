package app.lystproject.mobile.cat_search.presentation.viewstate

import app.lystproject.mobile.cat_search.ui.views.history.SearchHistoryViewState
import app.lystproject.mobile.cat_search.ui.views.history.SearchHistoryViewStateFactory
import app.lystproject.mobile.cat_search.ui.views.history.state
import app.lystproject.mobile.cat_search.ui.views.result.SearchResultViewState
import app.lystproject.mobile.cat_search.ui.views.result.SearchResultViewStateFactory
import app.lystproject.mobile.cat_search.ui.views.result.state


inline fun SearchScreenState.translateTo(
    transform: SearchScreenStateFactory.() -> SearchScreenState
): SearchScreenState = transform(SearchScreenStateFactory(this))

object SearchScreenStateFactory {

    private lateinit var state: SearchScreenState

    operator fun invoke(
        viewState: SearchScreenState
    ): SearchScreenStateFactory {
        state = viewState
        return this
    }

    val initialState: SearchScreenState
        get() = SearchScreenState()

    fun searchHistoryState(
        transform: SearchHistoryViewStateFactory.() -> SearchHistoryViewState
    ): SearchScreenState = state.copy(
        searchResultState = state.searchResultState.state { Hide },
        searchHistoryState = state.searchHistoryState.state(transform)
    )

    fun searchResultState(
        transform: SearchResultViewStateFactory.() -> SearchResultViewState
    ): SearchScreenState = state.copy(
        searchResultState = state.searchResultState.state(transform),
        searchHistoryState = state.searchHistoryState.state { Hide }
    )
}
