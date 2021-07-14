package app.lystproject.mobile.cat_details.presentation

import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.presentation.base.ViewResult


sealed class CatDetailViewResult : ViewResult {
    data class CatDetail(val cat: CatDetailModel) : CatDetailViewResult()
    data class FetchCatDetailError(
        val catName: String,
        val error: Throwable
    ) : CatDetailViewResult()

    object Retrying : CatDetailViewResult()
}