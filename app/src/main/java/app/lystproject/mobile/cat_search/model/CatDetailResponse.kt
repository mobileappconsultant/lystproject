package app.lystproject.mobile.cat_search.model

data class CatDetailResponse(
    val breeds: List<CatDetailModel>,
    val id: String,
    val url: String
)