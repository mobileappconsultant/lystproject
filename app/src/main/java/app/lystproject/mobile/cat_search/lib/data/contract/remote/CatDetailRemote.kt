package app.lystproject.mobile.cat_search.lib.data.contract.remote

import app.lystproject.mobile.cat_search.model.CatDetailResponse


internal interface CatDetailRemote {

    suspend fun fetchCat(catUrl: String): CatDetailResponse
}
