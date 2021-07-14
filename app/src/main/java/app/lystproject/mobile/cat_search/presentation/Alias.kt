package app.lystproject.mobile.cat_search.presentation

import app.lystproject.mobile.cat_search.presentation.viewstate.SearchScreenState
import app.lystproject.presentation.base.IntentProcessor
import app.lystproject.presentation.base.ViewStateReducer
import app.lystproject.presentation.stateMachine.StateMachine
import app.lystproject.presentation_android.ComponentManager

typealias SearchIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<SearchScreenResult>

typealias SearchStateReducer =
    @JvmSuppressWildcards ViewStateReducer<SearchScreenState, SearchScreenResult>

typealias SearchStateMachine =
    @JvmSuppressWildcards StateMachine<SearchScreenState, SearchScreenResult>

typealias SearchComponentManager =
    @JvmSuppressWildcards ComponentManager<SearchScreenState, SearchScreenResult>
