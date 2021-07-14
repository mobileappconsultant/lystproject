package app.lystproject.mobile.cat_search.lib.remote.di

import app.lystproject.mobile.BuildConfig
import app.lystproject.mobile.cat_search.lib.data.contract.remote.CatDetailRemote
import app.lystproject.mobile.cat_search.lib.data.contract.remote.SearchRemote
import app.lystproject.mobile.cat_search.lib.remote.ApiService
import app.lystproject.mobile.cat_search.lib.remote.impl.SearchRemoteImpl
import app.lystproject.mobile.cat_search.lib.remote.impl.CatDetailRemoteImpl
import app.lystproject.mobile.libraries.remote.RemoteFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface RemoteModule {

    @get:Binds
    val SearchRemoteImpl.bindSearchRemote: SearchRemote

    @get:Binds
    val CatDetailRemoteImpl.bindCatDetailRemote: CatDetailRemote

    companion object {
        @[Provides Singleton]
        fun apiService(remoteFactory: RemoteFactory): ApiService =
            remoteFactory.createRetrofit(
                url = BuildConfig.BASE_URL,
                isDebug = BuildConfig.DEBUG
            ).create(ApiService::class.java)
    }
}
