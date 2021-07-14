package app.lystproject.mobile.cat_search.ui.views.history

import androidx.core.view.isVisible
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.presentation.ClearSearchHistoryIntent
import app.lystproject.mobile.cat_search.presentation.UpdateHistoryIntent
import app.lystproject.mobile.cat_search.ui.adapters.SearchHistoryAdapter
import app.lystproject.mobile.core.extension.init
import app.lystproject.mobile.core.extension.show
import app.lystproject.mobile.databinding.LayoutSearchHistoryBinding
import app.lystproject.presentation_android.UIComponent

class SearchHistoryView(
    private val view: LayoutSearchHistoryBinding,
    navigationAction: (CatModel) -> Unit
) : UIComponent<SearchHistoryViewState>() {

    private val searchHistoryAdapter: SearchHistoryAdapter by init {
        SearchHistoryAdapter { model ->
            sendIntent(UpdateHistoryIntent(model))
            navigationAction(model)
        }
    }

    init {
        view.clearHistory.setOnClickListener { sendIntent(ClearSearchHistoryIntent) }
        view.searchHistoryRv.adapter = searchHistoryAdapter
    }

    override fun render(state: SearchHistoryViewState) {
        searchHistoryAdapter.submitList(state.history)
        view.run {
            recentSearchGroup.isVisible = state.showRecentSearchGroup
            searchHistoryRv.show = state.showHistory
            searchHistoryPrompt.isVisible = state.showHistoryPrompt
        }
    }
}
