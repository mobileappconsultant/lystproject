package app.lystproject.mobile.cat_details.presentation

import app.lystproject.mobile.cat_details.presentation.viewstate.CatDetailViewState
import app.lystproject.presentation.base.IntentProcessor
import app.lystproject.presentation.base.ViewStateReducer
import app.lystproject.presentation.stateMachine.StateMachine
import app.lystproject.presentation_android.ComponentManager

typealias CatDetailIntentProcessor =
        @JvmSuppressWildcards IntentProcessor<CatDetailViewResult>

typealias CatDetailStateReducer =
        @JvmSuppressWildcards ViewStateReducer<CatDetailViewState, CatDetailViewResult>

typealias CatDetailStateMachine =
        @JvmSuppressWildcards StateMachine<CatDetailViewState, CatDetailViewResult>

typealias DetailComponentManager =
        @JvmSuppressWildcards ComponentManager<CatDetailViewState, CatDetailViewResult>
