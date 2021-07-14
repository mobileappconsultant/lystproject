package app.lystproject.mobile.cat_search.ui.views.result

import app.lystproject.mobile.cat_search.model.CatModel


inline fun SearchResultViewState.state(
    transform: SearchResultViewStateFactory.() -> SearchResultViewState
): SearchResultViewState = transform(SearchResultViewStateFactory(this))

object SearchResultViewStateFactory {

    private lateinit var state: SearchResultViewState

    operator fun invoke(
        viewState: SearchResultViewState
    ): SearchResultViewStateFactory {
        state = viewState
        return this
    }

    val initialState: SearchResultViewState
        get() = SearchResultViewState()

    val Hide: SearchResultViewState
        get() = SearchResultViewState()

    val Searching: SearchResultViewState
        get() = state.copy(
            showProgress = state.searchResult.isEmpty(),
            showEmpty = false,
            showResult = state.searchResult.isNotEmpty(),
            showError = false,
            error = null
        )

    fun Error(
        message: String
    ): SearchResultViewState = state.copy(
        searchResult = emptyList(),
        showProgress = false,
        showEmpty = false,
        showResult = false,
        showError = true,
        error = message
    )

    fun ResultLoaded(
        cats: List<CatModel>
    ) = state.copy(
        searchResult = cats,
        showProgress = false,
        showEmpty = cats.isEmpty(),
        showResult = cats.isNotEmpty(),
        showError = false,
        error = null
    )
}
