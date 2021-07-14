package app.lystproject.presentation_android

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import app.lystproject.presentation.base.*
import app.lystproject.presentation.stateMachine.StateMachine

@MainThread
abstract class ComponentManager<S : ScreenState, out R : ViewResult>(
    private val stateMachine: StateMachine<S, R>
) : ViewModel(), MVIPresenter<S> {

    override fun <VS : ViewState> subscribe(
        component: Subscriber<VS>,
        stateTransform: StateTransform<S, VS>
    ) {
        stateMachine.subscribe(component, stateTransform)
    }

    override fun <VS : ViewState> subscribe(component: Subscriber<VS>) {
        stateMachine.subscribe(component, NoOpTransform())
    }

    fun disposeAll(owner: LifecycleOwner) {
        dispose(owner) { stateMachine.unSubscribeComponents() }
    }

    override fun onCleared() {
        stateMachine.unSubscribe()
    }
}
