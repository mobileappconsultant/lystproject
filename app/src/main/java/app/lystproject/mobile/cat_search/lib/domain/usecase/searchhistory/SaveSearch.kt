package app.lystproject.mobile.cat_search.lib.domain.usecase.searchhistory

import app.lystproject.mobile.cat_search.lib.domain.executor.PostExecutionThread
import app.lystproject.mobile.cat_search.lib.domain.repository.SearchHistoryRepository
import app.lystproject.mobile.cat_search.model.CatModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveSearch @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val postExecutionThread: PostExecutionThread
) {

    suspend operator fun invoke(cat: CatModel) {
        withContext(postExecutionThread.io) {
            searchHistoryRepository.saveSearch(cat)
        }
    }
}
