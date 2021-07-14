package app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory

import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClearSearchHistory @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val postExecutionThread: PostExecutionThread
) {

    suspend operator fun invoke() {
        withContext(postExecutionThread.io) {
            searchHistoryRepository.clearSearchHistory()
        }
    }
}
