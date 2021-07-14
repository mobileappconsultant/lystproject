package app.lystproject.mobile.cat_search.navigation

import app.lystproject.mobile.cat_search.model.CatModel

interface Navigator {
    fun openCatDetail(model: CatModel)
}
