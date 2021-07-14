package app.lystproject.mobile.cat_search.lib.data.repository

import app.lystproject.mobile.cat_search.lib.data.contract.cache.CatDetailCache
import app.lystproject.mobile.cat_search.lib.data.contract.remote.CatDetailRemote
import app.lystproject.mobile.cat_search.lib.domain.repository.CatDetailRepository
import app.lystproject.mobile.cat_search.model.CatDetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CatDetailRepositoryImpl @Inject constructor(
    private val catDetailRemote: CatDetailRemote,
    private val catDetailCache: CatDetailCache,
) : CatDetailRepository {

    override fun getCatDetail(reference_image_id: String, catId: String): Flow<CatDetailModel> {
        return flow {
            val cachedCat =
                catDetailCache.fetchCat(catId)
            if (cachedCat != null) {
                emit(cachedCat)
            } else {
                val catDetail =
                    catDetailRemote.fetchCat(reference_image_id)
                val cacheCatDetails = CatDetailModel(
                    imageUrl = catDetail.url,
                    id = catDetail.id,
                    reference_image_id = catDetail.breeds.first().reference_image_id,
                    wikipedia_url = catDetail.breeds.first().wikipedia_url,
                    description = catDetail.breeds.first().description,
                    name = catDetail.breeds.first().name,
                    origin = catDetail.breeds.first().origin,
                    energy_level = catDetail.breeds.first().energy_level
                )
                emit(cacheCatDetails)
                catDetailCache.saveCat(cacheCatDetails)
            }
        }
    }
}
