package app.lystproject.mobile.cat_search.presentation

import app.lystproject.mobile.cat_search.data.DummyData
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.presentation.viewstate.SearchScreenState
import app.lystproject.mobile.cat_search.presentation.viewstate.SearchScreenStateFactory
import app.lystproject.mobile.cat_search.presentation.viewstate.translateTo
import app.lystproject.mobile.libraries.testutils.ERROR_MSG
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class SearchScreenStateReducerTest {

    private val reducer = SearchScreenStateReducer()

    @Test
    fun check_that_emptySearchHistoryState_is_emitted_when_SearchHistoryResult_is_Empty() {
        runBlockingTest {
            val initialState: SearchScreenState = SearchScreenStateFactory.initialState
            val viewState: SearchScreenState = reducer.reduce(
                initialState,
                SearchScreenResult.LoadedHistory(emptyList())
            )
            assertThat(viewState).isEqualTo(
                initialState.translateTo {
                    searchHistoryState { DataLoaded(emptyList()) }
                }
            )
        }
    }

    @Test
    fun check_that_SearchHistoryLoadedState_is_emitted_when_SearchHistoryResult_is_Success() {
        runBlockingTest {
            val initialState: SearchScreenState = SearchScreenStateFactory.initialState
            val list: List<CatModel> = DummyData.catList
            val viewState: SearchScreenState = reducer.reduce(
                initialState,
                SearchScreenResult.LoadedHistory(list)
            )
            assertThat(viewState).isEqualTo(
                initialState.translateTo {
                    searchHistoryState { DataLoaded(list) }
                }
            )
        }
    }

    @Test
    fun check_that_SearchingState_is_emitted_when_SearchCatResult_is_Searching() {
        runBlockingTest {
            val initialState: SearchScreenState = SearchScreenStateFactory.initialState
            val viewState: SearchScreenState = reducer.reduce(
                initialState,
                SearchScreenResult.SearchCatResult.Searching
            )
            assertThat(viewState).isEqualTo(
                initialState.translateTo {
                    searchResultState { Searching }
                }
            )
        }
    }

    @Test
    fun check_that_SearchResultLoadedState_is_emitted_when_SearchCatResult_is_Success() {
        runBlockingTest {
            val initialState: SearchScreenState = SearchScreenStateFactory.initialState
            val list: List<CatModel> = DummyData.catList
            val viewState: SearchScreenState = reducer.reduce(
                initialState,
                SearchScreenResult.SearchCatResult.Success(list)
            )
            assertThat(viewState).isEqualTo(
                initialState.translateTo {
                    searchResultState { ResultLoaded(list) }
                }
            )
        }
    }

    @Test
    fun check_that_SearchResultErrorState_is_emitted_when_SearchCatResult_is_Error() {
        runBlockingTest {
            val initialState: SearchScreenState = SearchScreenStateFactory.initialState
            val viewState: SearchScreenState = reducer.reduce(
                initialState,
                SearchScreenResult.SearchCatResult.Error(Throwable(ERROR_MSG))
            )
            assertThat(viewState).isEqualTo(
                initialState.translateTo {
                    searchResultState { Error(ERROR_MSG) }
                }
            )
        }
    }

    @Test
    fun check_that_fall_back_error_message_is_returned_when_SearchCatResult_is_Error() {
        runBlockingTest {
            val initialState: SearchScreenState = SearchScreenStateFactory.initialState
            val viewState: SearchScreenState = reducer.reduce(
                initialState,
                SearchScreenResult.SearchCatResult.Error(Throwable())
            )
            assertThat(viewState).isEqualTo(
                initialState.translateTo {
                    searchResultState { Error("An error occurred") }
                }
            )
        }
    }
}
