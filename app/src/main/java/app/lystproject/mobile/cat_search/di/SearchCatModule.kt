package app.lystproject.mobile.cat_search.di

import app.lystproject.mobile.cat_search.presentation.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface SearchCatModule {

    @get:Binds
    val SearchScreenIntentProcessor.intentProcessor: SearchIntentProcessor

    @get:Binds
    val SearchScreenStateReducer.reducer: SearchStateReducer

    @get:Binds
    val SearchScreenStateMachine.stateMachine: SearchStateMachine
}
