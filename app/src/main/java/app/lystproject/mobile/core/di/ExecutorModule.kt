package app.lystproject.mobile.core.di

import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import app.lystproject.mobile.core.executor.PostExecutionThreadImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ExecutorModule {

    @get:[Binds Singleton]
    val PostExecutionThreadImpl.postExecutionThread: PostExecutionThread
}
