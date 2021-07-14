package app.lystproject.mobile.cat_details.presentation

import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_details.presentation.viewstate.CatDetailViewStateFactory
import app.lystproject.mobile.cat_details.ui.LoadCatDetailIntent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CatDetailViewStateMachine @AssistedInject constructor(
    intentProcessor: CatDetailIntentProcessor,
    reducer: CatDetailStateReducer,
    @Assisted cat: CatDetailModel
) : CatDetailStateMachine(
    intentProcessor,
    reducer,
    CatDetailViewStateFactory.initialState,
    LoadCatDetailIntent(cat)
) {

    @AssistedFactory
    interface Factory {
        fun create(cat: CatDetailModel): CatDetailViewStateMachine
    }
}
