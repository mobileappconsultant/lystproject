package app.lystproject.mobile.cat_search.lib.data.contract.remote

import app.lystproject.mobile.cat_search.model.CatModel


internal interface SearchRemote {
    suspend fun searchCats(catName: String): List<CatModel>
}
