package app.lystproject.mobile.cat_search.lib.remote.impl

import app.lystproject.mobile.cat_search.lib.data.contract.remote.CatDetailRemote
import app.lystproject.mobile.cat_search.lib.remote.ApiService
import app.lystproject.mobile.cat_search.model.CatDetailResponse
import app.lystproject.mobile.cat_search.model.CatModel
import javax.inject.Inject

internal class CatDetailRemoteImpl @Inject constructor(
    private val apiService: ApiService,
) : CatDetailRemote {

    override suspend fun fetchCat(catUrl: String): CatDetailResponse {
        return apiService.fetchCatDetail(catUrl)
    }


}
