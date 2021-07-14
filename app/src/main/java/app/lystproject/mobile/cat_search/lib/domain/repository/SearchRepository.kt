package app.lystproject.mobile.cat_search.lib.domain.repository

import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchCats(catName: String): Flow<List<CatModel>>
}
