package app.lystproject.mobile.cat_details.presentation.viewstate

import app.lystproject.mobile.cat_details.ui.views.detail.CatDetailsViewState
import app.lystproject.mobile.cat_details.ui.views.error.state
import app.lystproject.mobile.cat_details.ui.views.poster.PosterViewState


inline fun CatDetailViewState.translateTo(
    transform: CatDetailViewStateFactory.() -> CatDetailViewState
): CatDetailViewState = transform(
    CatDetailViewStateFactory(this)
)

object CatDetailViewStateFactory {

    private lateinit var state: CatDetailViewState

    operator fun invoke(
        viewState: CatDetailViewState
    ): CatDetailViewStateFactory {
        state = viewState
        return this
    }

    val initialState: CatDetailViewState
        get() = CatDetailViewState()

    fun catDetailsState(
        posterViewState: PosterViewState,
        catDetailsViewState: CatDetailsViewState
    ): CatDetailViewState = state.copy(
        posterViewState = posterViewState,
        catDetailViewState = catDetailsViewState
    )

    fun errorState(
        catName: String,
        error: String
    ): CatDetailViewState = state.copy(
        errorViewState = state.errorViewState.state { DisplayError(catName, error) },
    )

    val retryState: CatDetailViewState
        get() = state.copy(
            errorViewState = state.errorViewState.state { Hide },
        )
}
