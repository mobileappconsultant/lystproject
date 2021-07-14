package app.lystproject.mobile.cat_details.ui.views.error

import app.lystproject.presentation.base.ViewState

data class DetailErrorViewState(
    val catName: String = "",
    val errorMessage: String = "",
    val showError: Boolean = false
) : ViewState