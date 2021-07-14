package app.lystproject.mobile.cat_search.di.fakes

import app.lystproject.mobile.cat_search.lib.domain.repository.CatDetailRepository
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_search.ui.DummyData
import app.lystproject.mobile.libraries.testutils.ERROR_MSG
import app.lystproject.mobile.libraries.testutils.ResponseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

class FakeCatDetailRepository : CatDetailRepository {

    private var catsFlow: Flow<CatDetailModel> = flowOf(DummyData.catDetail)

    var catsResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            catsFlow = makeCatsResponse(value)
        }

    private fun makeCatsResponse(type: ResponseType): Flow<CatDetailModel> {
        return when (type) {
            ResponseType.DATA -> flowOf(DummyData.catDetail)
            ResponseType.EMPTY -> flowOf()
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }

    override fun getCatDetail(catId: String, reference_image_id: String): Flow<CatDetailModel> {
        return catsFlow
    }
}
