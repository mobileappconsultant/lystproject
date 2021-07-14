package app.lystproject.mobile.cat_details.presentation

import app.lystproject.mobile.cat_details.presentation.viewstate.CatDetailViewState
import app.lystproject.mobile.cat_details.presentation.viewstate.translateTo
import app.lystproject.mobile.cat_details.ui.views.detail.CatDetailsFactory
import app.lystproject.mobile.cat_details.ui.views.poster.PosterViewFactory
import app.lystproject.mobile.core.extension.errorMessage
import javax.inject.Inject

class CatDetailViewStateReducer @Inject constructor(
) : CatDetailStateReducer {

    override fun reduce(
        oldState: CatDetailViewState,
        result: CatDetailViewResult
    ): CatDetailViewState {
        return when (result) {
            is CatDetailViewResult.CatDetail -> oldState.translateTo {
                catDetailsState(
                    posterViewState = PosterViewFactory.create(model = result.cat),
                    catDetailsViewState = CatDetailsFactory.create(model = result.cat)
                )
            }
            is CatDetailViewResult.FetchCatDetailError -> oldState.translateTo {
                errorState(
                    catName = result.catName,
                    error = result.error.errorMessage
                )
            }
            CatDetailViewResult.Retrying -> oldState.translateTo { retryState }
        }
    }

}
