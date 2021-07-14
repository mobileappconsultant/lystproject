package app.lystproject.mobile.cat_details.ui.views.poster

import app.lystproject.mobile.cat_search.model.CatDetailModel


object PosterViewFactory {
    fun create(
        model: CatDetailModel
    ): PosterViewState {

        return PosterViewState(
            imageUrl = model.imageUrl ?: ""
        )
    }

    val initialState: PosterViewState
        get() = PosterViewState(
            imageUrl = ""
        )

}



