package app.lystproject.mobile.cat_search.lib.domain.repository

import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.flow.Flow

interface CatDetailRepository {

    fun getCatDetail(reference_image_id: String, catId: String): Flow<CatDetailModel>

}
