package app.lystproject.mobile.cat_search.remote

import app.lystproject.mobile.cat_search.lib.data.contract.remote.SearchRemote
import app.lystproject.mobile.cat_search.lib.remote.impl.SearchRemoteImpl
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.remote.utils.NO_MATCH_SEARCH_QUERY
import app.lystproject.mobile.cat_search.remote.utils.REQUEST_PATH
import app.lystproject.mobile.cat_search.remote.utils.RequestDispatcher
import app.lystproject.mobile.cat_search.remote.utils.SEARCH_QUERY
import app.lystproject.mobile.cat_search.remote.utils.SEARCH_RESPONSE_PATH
import app.lystproject.mobile.cat_search.remote.utils.adapter
import app.lystproject.mobile.cat_search.remote.utils.getJson
import app.lystproject.mobile.cat_search.remote.utils.makeTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var searchRemote: SearchRemote

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        searchRemote =
            SearchRemoteImpl(makeTestApiService(mockWebServer))
    }

    @Test
    fun `check that searchCats returns cat list of same size`() = runBlocking {
        val cats: List<CatModel> = searchRemote.searchCats(SEARCH_QUERY)
        val responseSize: Int =
            getResponseList(SEARCH_RESPONSE_PATH).size
        assertThat(cats).isNotEmpty()
        assertThat(cats.size).isEqualTo(responseSize)
    }

    @Test
    fun `check that calling searchcats makes request to correct path`() = runBlocking {
        searchRemote.searchCats(SEARCH_QUERY)
        assertThat("$REQUEST_PATH?q=$SEARCH_QUERY")
            .isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling searchcats makes a GET request`() = runBlocking {
        searchRemote.searchCats(SEARCH_QUERY)
        assertThat("GET $REQUEST_PATH?q=$SEARCH_QUERY HTTP/1.1")
            .isEqualTo(mockWebServer.takeRequest().requestLine)
    }

    @Test
    fun `check that searchcats returns empty list when no cat is found`() =
        runBlocking {
            val cats: List<CatModel> =
                searchRemote.searchCats(NO_MATCH_SEARCH_QUERY)
            assertThat(cats).isEmpty()
        }

    private fun getResponse(responsePath: String): List<CatModel> {
        return  adapter.fromJson(getJson(responsePath))!!
    }

    private fun getResponseList(responsePath: String): List<CatModel> {
        return getResponse(responsePath)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
