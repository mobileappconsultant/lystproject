package app.lystproject.mobile.cat_search.cache.mapper

import app.lystproject.mobile.cat_search.data.DummyData
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.libraries.cache.mapper.CatDetailCacheMapper
import app.lystproject.mobile.libraries.cache.model.CatDetailCacheModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CatDetailCacheMapperTest {

    private val mapper = CatDetailCacheMapper()

    @Test
    fun mapToModel() {
        val entity: CatDetailModel = DummyData.catDetail
        val model: CatDetailCacheModel = mapper.mapToModel(entity)
        assertThat(entity.imageUrl).isEqualTo(model.imageUrl)
        assertThat(entity.id).isEqualTo(model.id)
        assertThat(entity.name).isEqualTo(model.name)
        assertThat(entity.description).isEqualTo(model.description)
        assertThat(entity.reference_image_id).isEqualTo(model.reference_image_id)
        assertThat(entity.energy_level).isEqualTo(model.energy_level)
        assertThat(entity.origin).isEqualTo(model.origin)
    }

    @Test
    fun mapToEntity() {
        val model: CatDetailCacheModel = DummyData.catDetailCache
        val entity: CatDetailModel = mapper.mapToEntity(model)
        assertThat(model.imageUrl).isEqualTo(entity.imageUrl)
        assertThat(model.id).isEqualTo(entity.id)
        assertThat(entity.name).isEqualTo(model.name)
        assertThat(entity.description).isEqualTo(model.description)
        assertThat(entity.reference_image_id).isEqualTo(model.reference_image_id)
        assertThat(entity.energy_level).isEqualTo(model.energy_level)
        assertThat(entity.origin).isEqualTo(model.origin)
    }
}
