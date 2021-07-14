package app.lystproject.mobile.cat_search.di.fakes

import app.lystproject.mobile.cat_search.lib.domain.repository.SearchRepository
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.ui.DummyData
import app.lystproject.mobile.libraries.testutils.ERROR_MSG
import app.lystproject.mobile.libraries.testutils.ResponseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

class FakeSearchRepository : SearchRepository {

    private var catsFlow: Flow<List<CatModel>> =
        flowOf(DummyData.catList)

    var responseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            catsFlow = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Flow<List<CatModel>> {
        return when (type) {
            ResponseType.DATA -> flowOf(listOf(DummyData.cat))
            ResponseType.EMPTY -> flowOf(listOf())
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }

    override fun searchCats(catName: String): Flow<List<CatModel>> {
        return  catsFlow
    }
}
