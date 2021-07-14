package app.lystproject.mobile.cat_search.lib.domain.usecase.detail

import app.lystproject.mobile.cat_search.lib.domain.exception.requireParams
import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import app.lystproject.mobile.cat_search.lib.domain.repository.CatDetailRepository
import app.lystproject.mobile.cat_search.lib.domain.usecase.base.FlowUseCase
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatDetail @Inject constructor(
    private val catsDetailRepository: CatDetailRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<Map<String, String>, CatDetailModel>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: Map<String, String>?): Flow<CatDetailModel> {
        requireParams(params)
        return catsDetailRepository.getCatDetail(params["reference_image_id"]!!, params["catId"]!!)
    }
}
