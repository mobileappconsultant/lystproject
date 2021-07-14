package app.lystproject.mobile.cat_search.ui.views.search

import android.widget.EditText
import androidx.lifecycle.LifecycleCoroutineScope
import app.lystproject.mobile.cat_search.presentation.SearchIntent
import app.lystproject.presentation_android.StatelessUIComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchBarView(
    searchBar: EditText,
    coroutineScope: LifecycleCoroutineScope
) : StatelessUIComponent() {

    init {
        searchBar.textChanges
            .onEach { query -> sendIntent(SearchIntent(query)) }
            .launchIn(coroutineScope)
    }
}
