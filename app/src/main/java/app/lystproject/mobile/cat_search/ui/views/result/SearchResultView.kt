package app.lystproject.mobile.cat_search.ui.views.result

import androidx.core.view.isVisible
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.presentation.RetrySearchIntent
import app.lystproject.mobile.cat_search.presentation.SaveSearchIntent
import app.lystproject.mobile.cat_search.ui.adapters.SearchResultAdapter
import app.lystproject.mobile.core.extension.init
import app.lystproject.mobile.core.extension.show
import app.lystproject.mobile.databinding.LayoutSearchResultBinding
import app.lystproject.presentation_android.UIComponent

class SearchResultView(
    private val view: LayoutSearchResultBinding,
    query: () -> String,
    navigationAction: (CatModel) -> Unit
) : UIComponent<SearchResultViewState>() {

    private val searchResultAdapter: SearchResultAdapter by init {
        SearchResultAdapter { model ->
            sendIntent(SaveSearchIntent(model))
            navigationAction(model)
        }
    }

    init {
        view.catsRv.adapter = searchResultAdapter
        view.errorState.onRetry { sendIntent(RetrySearchIntent(query())) }
    }

    override fun render(state: SearchResultViewState) {
        searchResultAdapter.submitList(state.searchResult)
        view.run {
            catsRv.show = state.showResult
            progressBar.isVisible = state.showProgress
            emptyState.isVisible = state.showEmpty
            errorState.isVisible = state.showError
            errorState.setCaption(state.error)
        }
    }
}
