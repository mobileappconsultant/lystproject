package app.lystproject.mobile.libraries.cache.impl

import app.lystproject.mobile.cat_search.lib.data.contract.cache.CatDetailCache
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.libraries.cache.mapper.CatDetailCacheMapper
import app.lystproject.mobile.libraries.cache.room.CatDetailDao
import javax.inject.Inject

internal class CatDetailCacheImpl @Inject constructor(
    private val catDetailDao: CatDetailDao,
    private val catDetailCacheMapper: CatDetailCacheMapper
) : CatDetailCache {

    override suspend fun saveCat(catModel: CatDetailModel) {
        val catDetailModel =
            catDetailCacheMapper.mapToModel(catModel)
        catDetailDao.insertCat(catDetailModel)
    }

    override suspend fun fetchCat(catId: String): CatDetailModel? {
        val model = catDetailDao.fetchCat(catId)
        return model?.let { catDetailCacheMapper.mapToEntity(model) }
    }

}
