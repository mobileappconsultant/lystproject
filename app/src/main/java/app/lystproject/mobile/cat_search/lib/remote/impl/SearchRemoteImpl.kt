package app.lystproject.mobile.cat_search.lib.remote.impl

import app.lystproject.mobile.cat_search.lib.data.contract.remote.SearchRemote
import app.lystproject.mobile.cat_search.lib.remote.ApiService
import app.lystproject.mobile.cat_search.model.CatModel
import javax.inject.Inject

internal class SearchRemoteImpl @Inject constructor(
    private val apiService: ApiService,
) : SearchRemote {

    override suspend fun searchCats(catName: String): List<CatModel> {
        val catModels = apiService.searchCats(catName)
        return catModels.map {
            try {
                val imageUrl = it.reference_image_id?.let { it1 -> apiService.getCatImageUrl(it1) }
                it.imageUrl = imageUrl?.url
            } catch (e: Exception) {
                e.printStackTrace()
            }
            it
        }
    }
}
