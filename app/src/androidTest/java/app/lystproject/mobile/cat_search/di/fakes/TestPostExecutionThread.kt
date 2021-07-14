package app.lystproject.mobile.cat_search.di.fakes

import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestPostExecutionThread : PostExecutionThread {

    override val main: CoroutineDispatcher
        get() = TestCoroutineDispatcher()

    override val io: CoroutineDispatcher
        get() = TestCoroutineDispatcher()

    override val default: CoroutineDispatcher
        get() = TestCoroutineDispatcher()
}
