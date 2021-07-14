package app.lystproject.presentation.stateMachine

import app.lystproject.presentation.base.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

public abstract class StateMachine<S : ScreenState, R : ViewResult>(
    private val intentProcessor: IntentProcessor<R>,
    private val reducer: ViewStateReducer<S, R>,
    initialState: S,
    initialIntent: ViewIntent = NoOpIntent,
    config: Config = NoOpConfig
) {

    private val mainScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate
    )

    private val intents = Channel<ViewIntent>(capacity = 1).apply {
        trySend(initialIntent)
    }

    private val subscriptionManager = SubscriptionManager(mainScope, initialState)

    init {
        intents.receiveAsFlow()
            .filter { it !is NoOpIntent }
            .mapConfig(config, intentProcessor::intentToResult)
            .scan(initialState, reducer::reduce)
            .distinctUntilChanged()
            .onEach(subscriptionManager::updateState)
            .launchIn(
                CoroutineScope(
                    mainScope.coroutineContext[Job]!! + Dispatchers.IO
                )
            )
    }

    @Suppress("UNCHECKED_CAST")
    public fun <V : ViewState> subscribe(
        subscriber: Subscriber<V>,
        transform: StateTransform<S, V>
    ) {
        subscriptionManager.subscribe(
            subscriber as Subscriber<ViewState>,
            transform as StateTransform<ScreenState, ViewState>,
            intents::offer
        )
    }

    public fun unSubscribe() {
        unSubscribeComponents()
        mainScope.cancel()
    }

    public fun unSubscribeComponents() {
        subscriptionManager.unSubscribe()
    }
}
