package app.lystproject.mobile.cat_search.domain.usecase.fakes

import app.lystproject.mobile.cat_search.lib.domain.exception.requireParams
import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import app.lystproject.mobile.cat_search.lib.domain.usecase.base.FlowUseCase
import app.lystproject.mobile.libraries.testutils.ERROR_MSG
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

internal class ExceptionUseCase(private val postExecutionThread: PostExecutionThread) :
    FlowUseCase<Unit, Unit>() {

    override fun execute(params: Unit?): Flow<Unit> {
        return flow {
            throw SocketTimeoutException(ERROR_MSG)
        }
    }

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io
}

internal class ParamUseCase(private val postExecutionThread: PostExecutionThread) :
    FlowUseCase<String, String>() {

    override fun execute(params: String?): Flow<String> {
        requireParams(params)
        return flowOf(params)
    }

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io
}