package app.lystproject.mobile.cat_search.domain.usecase

import app.lystproject.mobile.cat_search.TestPostExecutionThread
import app.lystproject.mobile.cat_search.data.DummyData
import app.lystproject.mobile.cat_search.fakes.FakeSearchRepository
import app.lystproject.mobile.cat_search.lib.domain.exception.NoParamsException
import app.lystproject.mobile.cat_search.lib.domain.exception.noParamMessage
import app.lystproject.mobile.cat_search.lib.domain.usecase.search.SearchCats
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.libraries.testutils.ERROR_MSG
import app.lystproject.mobile.libraries.testutils.ResponseType
import app.lystproject.mobile.libraries.testutils.assertThrows
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlinx.coroutines.flow.collect
import java.net.SocketTimeoutException

class SearchCatsTest {

    private val fakeCatsRepository = FakeSearchRepository()

    private val searchCats =
        SearchCats(fakeCatsRepository, TestPostExecutionThread())

    @Test
    fun `check that calling searchCats returns list of cats`() = runBlockingTest {
        fakeCatsRepository.responseType = ResponseType.DATA
        val cats: List<CatModel> = searchCats(DummyData.name).first()
        assertThat(cats.size).isAtLeast(1)
    }

    @Test
    fun `check that calling searchCats returns correct data`() = runBlockingTest {
        fakeCatsRepository.responseType = ResponseType.DATA
        val cats: List<CatModel> = searchCats(DummyData.name).first()
        val cat: CatModel = cats.first()
        assertThat(cat.name).isEqualTo(DummyData.cat.name)
        assertThat(cat.id).isEqualTo(DummyData.cat.id)
        assertThat(cat.imageUrl).isEqualTo(DummyData.cat.imageUrl)
        assertThat(cat.reference_image_id).isEqualTo(DummyData.cat.reference_image_id)
    }

    @Test
    fun `check that calling searchCats returns empty list if response is empty`() =
        runBlockingTest {
            fakeCatsRepository.responseType = ResponseType.EMPTY
            val cats: List<CatModel> = searchCats(DummyData.name).first()
            assertThat(cats).isEmpty()
        }

    @Test
    fun `check that calling searchCats returns error if call fails`() = runBlockingTest {
        fakeCatsRepository.responseType = ResponseType.ERROR
        val exception: SocketTimeoutException = assertThrows {
            searchCats(DummyData.name).collect()
        }
        assertThat(exception).hasMessageThat().isEqualTo(ERROR_MSG)
    }

    @Test
    fun `check that calling searchCats without id throws NoParamException`() =
        runBlockingTest {
            val exception: NoParamsException = assertThrows {
                searchCats().collect()
            }
            assertThat(exception).hasMessageThat().isEqualTo(noParamMessage)
        }
}
