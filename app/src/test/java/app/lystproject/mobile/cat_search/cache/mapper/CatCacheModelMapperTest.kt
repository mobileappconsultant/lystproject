package app.lystproject.mobile.cat_search.cache.mapper

import app.lystproject.mobile.cat_search.data.DummyData
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.libraries.cache.mapper.CatModelMapper
import app.lystproject.mobile.libraries.cache.model.CatCacheModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CatCacheModelMapperTest {

    private val catCacheModelMapper =
        CatModelMapper()

    @Test
    fun `check that mapToModel returns correct data`() {
        val entity: CatModel = DummyData.cat2
        val model: CatCacheModel = catCacheModelMapper.mapToModel(entity)
        assertThat(entity.name).isEqualTo(model.name)
        assertThat(entity.imageUrl).isEqualTo(model.imageUrl)
        assertThat(entity.id).isEqualTo(model.id)
        assertThat(entity.reference_image_id).isEqualTo(model.reference_image_id)
        assertThat(entity.origin).isEqualTo(model.origin)
    }

    @Test
    fun `check that mapToEntity returns correct data`() {
        val model: CatCacheModel = DummyData.cacheModel
        val entity: CatModel = catCacheModelMapper.mapToEntity(model)
        assertThat(model.name).isEqualTo(entity.name)
        assertThat(model.origin).isEqualTo(entity.origin)
        assertThat(model.id).isEqualTo(entity.id)
        assertThat(model.reference_image_id).isEqualTo(entity.reference_image_id)
        assertThat(model.imageUrl).isEqualTo(entity.imageUrl)
    }
}
