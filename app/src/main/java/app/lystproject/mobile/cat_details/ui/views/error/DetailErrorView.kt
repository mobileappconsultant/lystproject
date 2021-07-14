package app.lystproject.mobile.cat_details.ui.views.error

import androidx.core.view.isVisible
import app.lystproject.mobile.R
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.core.EmptyStateView
import app.lystproject.presentation_android.UIComponent

class DetailErrorView(
    private val view: EmptyStateView,
    cat: CatDetailModel
) : UIComponent<DetailErrorViewState>() {

    init {
        view.onRetry {
            sendIntent(RetryFetchCatDetailsIntent(cat))
        }
    }

    override fun render(state: DetailErrorViewState) {
        view.run {
            isVisible = state.showError
            setCaption(state.errorMessage)
            setTitle(
                context.getString(
                    R.string.error_fetching_details,
                    state.catName
                )
            )
        }
    }
}
