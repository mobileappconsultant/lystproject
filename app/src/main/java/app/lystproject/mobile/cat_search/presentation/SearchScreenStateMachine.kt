package app.lystproject.mobile.cat_search.presentation

import app.lystproject.mobile.cat_search.presentation.viewstate.SearchScreenStateFactory
import app.lystproject.presentation.stateMachine.Latest
import javax.inject.Inject

class SearchScreenStateMachine @Inject constructor(
    intentProcessor: SearchIntentProcessor,
    reducer: SearchStateReducer
) : SearchStateMachine(
    intentProcessor,
    reducer,
    SearchScreenStateFactory.initialState,
    LoadSearchHistory,
    Latest
)
