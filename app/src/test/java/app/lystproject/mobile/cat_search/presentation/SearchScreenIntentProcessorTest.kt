package app.lystproject.mobile.cat_search.presentation

import app.lystproject.mobile.cat_search.TestPostExecutionThread
import app.lystproject.mobile.cat_search.data.DummyData
import app.lystproject.mobile.cat_search.fakes.FakeSearchHistoryRepository
import app.lystproject.mobile.cat_search.fakes.FakeSearchRepository
import app.lystproject.mobile.cat_search.lib.domain.usecase.search.SearchCats
import app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory.ClearSearchHistory
import app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory.GetSearchHistory
import app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory.SaveSearch
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.presentation.SearchScreenResult.SearchCatResult
import app.lystproject.mobile.libraries.testutils.*
import app.lystproject.presentation.base.ViewIntent
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.net.SocketTimeoutException

class SearchScreenIntentProcessorTest {

    private val fakeCatRepository = FakeSearchRepository()

    private val fakeSearchHistoryRepository = FakeSearchHistoryRepository()

    private val testPostExecutionThread = TestPostExecutionThread()

    private val searchViewIntentProcessor =
        SearchScreenIntentProcessor(
            SearchCats(fakeCatRepository, testPostExecutionThread),
            SaveSearch(fakeSearchHistoryRepository, testPostExecutionThread),
            GetSearchHistory(fakeSearchHistoryRepository, testPostExecutionThread),
            ClearSearchHistory(fakeSearchHistoryRepository, testPostExecutionThread),
        )

    private val resultRecorder: FlowRecorder<SearchScreenResult> = FlowRecorder(TestCoroutineScope())

    @Test
    fun `check that LoadSearchHistoryIntent returns SuccessResult`() = runBlockingTest {
        val list: List<CatModel> = DummyData.catList
        fakeSearchHistoryRepository.saveSearch(list.first())
        recordSearchHistoryResult(LoadSearchHistory)
        assertThat(resultRecorder.takeAll()).containsElements(SearchScreenResult.LoadedHistory(list))
    }

    @Test
    fun `check that LoadSearchHistoryIntent returns EmptyResult`() = runBlockingTest {
        recordSearchHistoryResult(LoadSearchHistory)
        assertThat(resultRecorder.takeAll())
            .containsElements(SearchScreenResult.LoadedHistory(emptyList()))
    }

    @Test
    fun `check_that ClearSearchHistoryIntent returns EmptyResult`() = runBlockingTest {
        val list: List<CatModel> = DummyData.catList
        fakeSearchHistoryRepository.saveSearch(list.first())
        recordSearchHistoryResult(ClearSearchHistoryIntent)
        assertThat(resultRecorder.takeAll())
            .containsElements(SearchScreenResult.LoadedHistory(emptyList()))
    }

    @Test
    fun `check that UpdateHistoryIntent returns History in order`() = runBlockingTest {
        val first: CatModel = DummyData.cat
        val second: CatModel = DummyData.cat.copy(name = "amig")
        val third: CatModel = DummyData.cat.copy(reference_image_id = "xxcctg")
        fakeSearchHistoryRepository.saveSearch(first)
        fakeSearchHistoryRepository.saveSearch(second)
        fakeSearchHistoryRepository.saveSearch(third)
        recordSearchHistoryResult(UpdateHistoryIntent(second))
        assertThat(resultRecorder.takeAll())
            .containsElements(SearchScreenResult.LoadedHistory(listOf(second)))
    }

    @Test
    fun `check that SearchCatIntent returns SuccessResult`() {
        recordSearchResult(SearchIntent(DummyData.query), ResponseType.DATA)
        assertThat(resultRecorder.takeAll())
            .containsElements(
                SearchCatResult.Searching,
                SearchCatResult.Success(DummyData.catList)
            )
    }

    @Test
    fun `SearchCatIntent returns emptySearchHistoryResult when query is empty and no search is saved`() {
        searchViewIntentProcessor.intentToResult(SearchIntent(""))
            .recordWith(resultRecorder)
        assertThat(resultRecorder.takeAll())
            .containsElements(SearchScreenResult.LoadedHistory(emptyList()))
    }

    @Test
    fun `SearchCatIntent returns loadedSearchHistoryResult when query is empty and search is saved`() =
        runBlockingTest {
            val list: List<CatModel> = DummyData.catList
            fakeSearchHistoryRepository.saveSearch(list.first())
            searchViewIntentProcessor.intentToResult(SearchIntent(""))
                .recordWith(resultRecorder)
            assertThat(resultRecorder.takeAll()).containsElements(
                SearchScreenResult.LoadedHistory(list)
            )
        }

    @Test
    fun `check that SearchCatIntent returns ErrorResult`() {
        recordSearchResult(SearchIntent(DummyData.query), ResponseType.ERROR)
        val results: List<SearchScreenResult> = resultRecorder.takeAll()
        assertThat(results.map { it.javaClass })
            .containsElements(
                SearchCatResult.Searching::class.java,
                SearchCatResult.Error::class.java
            )
        val errorResult: SearchCatResult.Error = results.last() as SearchCatResult.Error
        assertThat(errorResult.throwable).isInstanceOf(SocketTimeoutException::class.java)
        assertThat(errorResult.throwable.message).isEqualTo(ERROR_MSG)
    }

    @Test
    fun `check that SaveSearchIntent saves current cat`() = runBlockingTest {
        val list: List<CatModel> = DummyData.catList
        searchViewIntentProcessor.intentToResult(
            SaveSearchIntent(list.first())
        ).collect()
        recordSearchHistoryResult(LoadSearchHistory)
        assertThat(resultRecorder.takeAll()).containsElements(SearchScreenResult.LoadedHistory(list))
    }

    private fun recordSearchResult(intent: ViewIntent, type: ResponseType) {
        fakeCatRepository.responseType = type
        searchViewIntentProcessor.intentToResult(intent).recordWith(resultRecorder)
    }

    private fun recordSearchHistoryResult(intent: ViewIntent) {
        searchViewIntentProcessor.intentToResult(intent).recordWith(resultRecorder)
    }
}
