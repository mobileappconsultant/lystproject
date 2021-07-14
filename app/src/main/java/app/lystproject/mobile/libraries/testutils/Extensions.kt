package app.lystproject.mobile.libraries.testutils

import com.google.common.truth.IterableSubject
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.function.ThrowingRunnable

@OptIn(ExperimentalCoroutinesApi::class)
public inline fun <reified T : Throwable> TestCoroutineScope.assertThrows(
    crossinline runnable: suspend () -> Unit
): T {
    val throwingRunnable = ThrowingRunnable {
        val job: Deferred<Unit> = async { runnable() }
        job.getCompletionExceptionOrNull()?.run { throw this }
        job.cancel()
    }
    return Assert.assertThrows(T::class.java, throwingRunnable)
}

public inline fun <reified T> IterableSubject.containsElements(vararg instance: T) {
    containsExactlyElementsIn(instance).inOrder()
}
