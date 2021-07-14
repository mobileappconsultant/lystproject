package app.lystproject.mobile.cat_search.lib.data.repository

import app.lystproject.mobile.cat_search.lib.domain.repository.SearchRepository
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.lib.data.contract.remote.SearchRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchRemote: SearchRemote,
) : SearchRepository {

    override fun searchCats(catName: String): Flow<List<CatModel>> {
        return flow {
            emit(searchRemote.searchCats(catName))
        }
    }
}
