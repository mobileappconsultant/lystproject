package app.lystproject.mobile.cat_details.di

import app.lystproject.mobile.cat_details.presentation.CatDetailIntentProcessor
import app.lystproject.mobile.cat_details.presentation.CatDetailStateReducer
import app.lystproject.mobile.cat_details.presentation.CatDetailViewStateReducer
import app.lystproject.mobile.cat_details.presentation.CatDetailViewIntentProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
interface CatDetailModule {

    @get:Binds
    val CatDetailViewIntentProcessor.intentProcessor: CatDetailIntentProcessor

    @get:Binds
    val CatDetailViewStateReducer.reducer: CatDetailStateReducer
}
