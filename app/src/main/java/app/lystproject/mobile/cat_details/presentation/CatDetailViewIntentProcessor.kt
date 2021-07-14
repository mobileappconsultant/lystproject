package app.lystproject.mobile.cat_details.presentation

import app.lystproject.mobile.cat_details.ui.LoadCatDetailIntent
import app.lystproject.mobile.cat_details.ui.views.error.RetryFetchCatDetailsIntent
import app.lystproject.mobile.cat_search.lib.domain.usecase.detail.GetCatDetail
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.presentation.base.InvalidViewIntentException
import app.lystproject.presentation.base.ViewIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CatDetailViewIntentProcessor @Inject constructor(
    private val getCatDetail: GetCatDetail
) : CatDetailIntentProcessor {

    override fun intentToResult(viewIntent: ViewIntent): Flow<CatDetailViewResult> {
        return when (viewIntent) {
            is LoadCatDetailIntent -> {
                getCatInfo(
                    model = viewIntent.cat,
                    initialResult = CatDetailViewResult.CatDetail(
                        cat = viewIntent.cat
                    )
                )
            }
            is RetryFetchCatDetailsIntent -> {
                getCatInfo(
                    model = viewIntent.cat,
                    initialResult = CatDetailViewResult.Retrying
                )
            }
            else -> throw InvalidViewIntentException(viewIntent)
        }
    }

    private fun getCatInfo(
        model: CatDetailModel,
        initialResult: CatDetailViewResult
    ): Flow<CatDetailViewResult> {
        return getCatDetail(mapOf("reference_image_id" to model.reference_image_id!!, "catId" to model.id)).map {
            if (it.id.isNotEmpty()) {
                CatDetailViewResult.CatDetail(it)
            } else {
                CatDetailViewResult.FetchCatDetailError(
                    model.name, Throwable("Invalid cat id")
                )
            }

        }
            .onStart { emit(initialResult) }
            .catch { error ->
                emit(
                    CatDetailViewResult.FetchCatDetailError(
                        model.name, error
                    )
                )
            }
    }

}
