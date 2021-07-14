package app.lystproject.mobile.cat_search.domain.usecase

import app.lystproject.mobile.cat_search.TestPostExecutionThread
import app.lystproject.mobile.cat_search.domain.usecase.fakes.ExceptionUseCase
import app.lystproject.mobile.cat_search.domain.usecase.fakes.ParamUseCase
import app.lystproject.mobile.cat_search.lib.domain.exception.NoParamsException
import app.lystproject.mobile.libraries.testutils.ERROR_MSG
import app.lystproject.mobile.libraries.testutils.assertThrows
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.net.SocketTimeoutException

class FlowUseCaseTest {

    @Test
    fun `check that ExceptionUseCase throws exception`() = runBlockingTest {
        val exception: SocketTimeoutException = assertThrows {
            ExceptionUseCase(TestPostExecutionThread())().collect()
        }
        assertThat(exception)
            .hasMessageThat()
            .isEqualTo(ERROR_MSG)
    }

    @Test(expected = NoParamsException::class)
    fun `check that calling ParamUseCase without params throws exception`() = runBlockingTest {
        ParamUseCase(TestPostExecutionThread())()
    }

    @Test
    fun `check that ParamsUseCase returns correct data`() = runBlockingTest {
        val param = "Correct data"
        val useCase = ParamUseCase(TestPostExecutionThread())
        val result: String = useCase(param).first()
        assertThat(result).isEqualTo(param)
    }
}
