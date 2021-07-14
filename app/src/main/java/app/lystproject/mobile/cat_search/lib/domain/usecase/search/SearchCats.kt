package app.lystproject.mobile.cat_search.lib.domain.usecase.search

import app.lystproject.mobile.cat_search.lib.domain.exception.requireParams
import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchRepository
import app.lystproject.mobile.cat_search.lib.domain.usecase.base.FlowUseCase
import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCats @Inject constructor(
    private val repository: SearchRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<String, List<CatModel>>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: String?): Flow<List<CatModel>> {
        requireParams(params)
        return repository.searchCats(params)
    }
}
