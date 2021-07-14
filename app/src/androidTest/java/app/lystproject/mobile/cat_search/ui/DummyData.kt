package app.lystproject.mobile.cat_search.ui

import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.libraries.testutils.ERROR_MSG
import java.net.SocketTimeoutException

internal object DummyData {
    val cat = CatModel(
        "Abyssinian",
        "abys",
        "0XYvRd7oD",
        "Egypt",
        ""
    )
    val cat2 = CatModel(
        "Abyssinian Green",
        "abys2",
        "0XYvRd7oD",
        "Egypt",
        "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg"
    )

    val catList: List<CatModel> = listOf(cat)

    const val query = "Abyssinian"

    val catDetail = CatDetailModel(
        "","","","","" ,
                0F,"",
        "abyss"
    )


    val exception: SocketTimeoutException
        get() = SocketTimeoutException(ERROR_MSG)
}