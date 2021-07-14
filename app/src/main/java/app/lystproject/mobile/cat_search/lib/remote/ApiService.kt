package app.lystproject.mobile.cat_search.lib.remote

import app.lystproject.mobile.cat_search.model.CatDetailResponse
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.model.CatModelWithImage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

internal interface ApiService {

    @GET("breeds/search")
    suspend fun searchCats(@Query("q") params: String): List<CatModel>

    @GET("images/{id}")
    suspend fun getCatImageUrl(@Path("id") imageId: String) : CatModelWithImage

    @GET("images/{id}")
    suspend fun fetchCatDetail(@Path("id") imageId: String) : CatDetailResponse

}
