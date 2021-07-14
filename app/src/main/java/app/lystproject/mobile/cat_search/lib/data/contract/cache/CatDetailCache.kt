package app.lystproject.mobile.cat_search.lib.data.contract.cache

import app.lystproject.mobile.cat_search.model.CatDetailModel


internal interface CatDetailCache {
    suspend fun saveCat(catModel: CatDetailModel)
    suspend fun fetchCat(catId: String): CatDetailModel?
}
