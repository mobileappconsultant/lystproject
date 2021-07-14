package app.lystproject.mobile.cat_search.presentation

import app.lystproject.mobile.cat_search.presentation.SearchScreenResult.LoadedHistory
import app.lystproject.mobile.cat_search.presentation.SearchScreenResult.SearchCatResult.Error
import app.lystproject.mobile.cat_search.presentation.SearchScreenResult.SearchCatResult.Searching
import app.lystproject.mobile.cat_search.presentation.SearchScreenResult.SearchCatResult.Success
import app.lystproject.mobile.cat_search.presentation.viewstate.SearchScreenState
import app.lystproject.mobile.cat_search.presentation.viewstate.translateTo
import app.lystproject.mobile.core.extension.errorMessage
import javax.inject.Inject

class SearchScreenStateReducer @Inject constructor(
) : SearchStateReducer {

    override fun reduce(
        oldState: SearchScreenState,
        result: SearchScreenResult
    ): SearchScreenState {
        return when (result) {
            is LoadedHistory -> oldState.translateTo {
                searchHistoryState {
                    DataLoaded(result.searchHistory)
                }
            }
            Searching -> oldState.translateTo {
                searchResultState { Searching }
            }
            is Error -> oldState.translateTo {
                searchResultState { Error(result.throwable.errorMessage) }
            }
            is Success -> oldState.translateTo {
                searchResultState { ResultLoaded(result.cats) }
            }
        }
    }
}
