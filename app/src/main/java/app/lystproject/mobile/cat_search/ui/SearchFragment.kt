package app.lystproject.mobile.cat_search.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.lystproject.mobile.R
import app.lystproject.mobile.cat_search.navigation.Navigator
import app.lystproject.mobile.cat_search.presentation.SearchComponentManager
import app.lystproject.mobile.cat_search.presentation.SearchStateMachine
import app.lystproject.mobile.cat_search.presentation.viewstate.SearchScreenState
import app.lystproject.mobile.cat_search.ui.views.history.SearchHistoryView
import app.lystproject.mobile.cat_search.ui.views.result.SearchResultView
import app.lystproject.mobile.cat_search.ui.views.search.SearchBarView
import app.lystproject.mobile.core.extension.lazyText
import app.lystproject.mobile.core.extension.onBackPress
import app.lystproject.mobile.core.extension.viewScope
import app.lystproject.mobile.databinding.FragmentSearchBinding
import app.lystproject.mobile.databinding.LayoutSearchHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatSearchComponentManager @Inject constructor(
    searchStateMachine: SearchStateMachine
) : SearchComponentManager(searchStateMachine)


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val componentManager: CatSearchComponentManager by viewModels()

        val binding = FragmentSearchBinding.bind(view)

        handleBackPress(binding.searchBar)

        componentManager.run {
            subscribe(
                component = SearchBarView(
                    searchBar = binding.searchBar,
                    coroutineScope = viewScope
                )
            )
            subscribe(
                component = SearchHistoryView(
                    view = binding.recentSearch,
                    navigationAction = navigator::openCatDetail
                ),
                stateTransform = SearchScreenState::searchHistoryState
            )
            subscribe(
                component = SearchResultView(
                    view = binding.searchResult,
                    query = binding.searchBar.lazyText,
                    navigationAction = navigator::openCatDetail
                ),
                stateTransform = SearchScreenState::searchResultState
            )
            disposeAll(viewLifecycleOwner)
        }
    }

    private fun handleBackPress(editText: EditText) {
        onBackPress {
            if (editText.text.isNotEmpty()) {
                editText.text.clear()
            } else {
                requireActivity().finish()
            }
        }
    }
}