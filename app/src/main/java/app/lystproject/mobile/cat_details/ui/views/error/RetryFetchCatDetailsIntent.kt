package app.lystproject.mobile.cat_details.ui.views.error

import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.presentation.base.ViewIntent

data class RetryFetchCatDetailsIntent(
    val cat: CatDetailModel
) : ViewIntent
