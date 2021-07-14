package app.lystproject.mobile.libraries.cache.mapper

import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.libraries.cache.model.CatDetailCacheModel
import javax.inject.Inject

internal class CatDetailCacheMapper @Inject constructor() :
    CacheModelMapper<CatDetailCacheModel, CatDetailModel> {

    override fun mapToModel(entity: CatDetailModel): CatDetailCacheModel {
        return CatDetailCacheModel(
            imageUrl = entity.imageUrl ?: "",
            id = entity.id,
            reference_image_id = entity.reference_image_id,
            wikipedia_url = entity.wikipedia_url,
            name = entity.name,
            origin = entity.origin,
            energy_level = entity.energy_level,
            description = entity.description
        )
    }

    override fun mapToEntity(model: CatDetailCacheModel): CatDetailModel {
        return CatDetailModel(
            imageUrl = model.imageUrl,
            id = model.id,
            reference_image_id = model.reference_image_id,
            energy_level = model.energy_level,
            origin = model.origin,
            name = model.name,
            description = model.description,
            wikipedia_url = model.wikipedia_url
        )
    }
}
