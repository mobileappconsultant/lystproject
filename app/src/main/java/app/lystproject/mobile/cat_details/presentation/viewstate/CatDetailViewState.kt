package app.lystproject.mobile.cat_details.presentation.viewstate

import app.lystproject.mobile.cat_details.ui.views.detail.CatDetailsFactory
import app.lystproject.mobile.cat_details.ui.views.detail.CatDetailsViewState
import app.lystproject.mobile.cat_details.ui.views.error.DetailErrorViewState
import app.lystproject.mobile.cat_details.ui.views.error.DetailErrorViewStateFactory
import app.lystproject.mobile.cat_details.ui.views.poster.PosterViewFactory
import app.lystproject.mobile.cat_details.ui.views.poster.PosterViewState
import app.lystproject.presentation.base.ScreenState

data class CatDetailViewState(
    val posterViewState: PosterViewState = PosterViewFactory.initialState,
    val catDetailViewState: CatDetailsViewState = CatDetailsFactory.initialState,
    val errorViewState: DetailErrorViewState = DetailErrorViewStateFactory.initialState
) : ScreenState
