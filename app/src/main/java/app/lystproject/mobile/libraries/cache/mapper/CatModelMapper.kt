package app.lystproject.mobile.libraries.cache.mapper

import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.libraries.cache.model.CatCacheModel
import javax.inject.Inject

internal class CatModelMapper @Inject constructor() :
    CacheModelMapper<CatCacheModel, CatModel> {

    @Throws(NumberFormatException::class)
    override fun mapToModel(entity: CatModel): CatCacheModel {
        return CatCacheModel(
            name = entity.name,
            id = entity.id,
            imageUrl = entity.imageUrl,
            reference_image_id = entity.reference_image_id,
            origin = entity.origin
        )
    }

    override fun mapToEntity(model: CatCacheModel): CatModel {
        return CatModel(
            model.name,
            model.id,
            model.reference_image_id,
            model.origin,
            model.imageUrl,
        )
    }
}
