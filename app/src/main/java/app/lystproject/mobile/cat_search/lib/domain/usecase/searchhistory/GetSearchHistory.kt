package app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory

import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchHistoryRepository
import app.lystproject.mobile.cat_search.lib.domain.usecase.base.FlowUseCase
import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchHistory @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<CatModel>>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: Unit?): Flow<List<CatModel>> {
        return searchHistoryRepository.getSearchHistory()
    }
}
