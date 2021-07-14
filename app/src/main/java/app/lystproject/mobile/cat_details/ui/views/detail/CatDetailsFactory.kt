package app.lystproject.mobile.cat_details.ui.views.detail

import app.lystproject.mobile.cat_search.model.CatDetailModel

object CatDetailsFactory {

    fun create(
        model: CatDetailModel
    ): CatDetailsViewState {

        return CatDetailsViewState(
            name = model.name,
            country = model.origin,
            energyLevel = model.energy_level,
            description = model.description,
            wikipedia_url = model.wikipedia_url
        )
    }

    val initialState: CatDetailsViewState
        get() = CatDetailsViewState(
            name = "",
            country = "",
            energyLevel = 0F,
            description = "",
            wikipedia_url = ""
        )
}