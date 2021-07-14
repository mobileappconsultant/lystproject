package app.lystproject.mobile.cat_details.ui.views.detail

import app.lystproject.presentation.base.ViewState

data class CatDetailsViewState(
    val name: String,
    val country: String,
    val energyLevel: Float?,
    val description: String,
    val wikipedia_url: String
): ViewState