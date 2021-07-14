package app.lystproject.mobile.navigation.di

import androidx.navigation.NavController
import app.lystproject.mobile.cat_search.navigation.Navigator
import app.lystproject.mobile.core.extension.NavigateBack
import app.lystproject.mobile.navigation.SearchScreenNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
internal interface NavigationModule {

    @get:Binds
    val SearchScreenNavigator.searchScreenNavigator: Navigator

    companion object {
        @Provides
        fun provideBackNav(
            navController: NavController
        ): NavigateBack = {
            navController.navigateUp()
        }
    }
}