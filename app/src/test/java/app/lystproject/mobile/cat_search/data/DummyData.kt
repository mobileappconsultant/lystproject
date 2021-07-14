package app.lystproject.mobile.cat_search.data

import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.libraries.cache.model.CatCacheModel
import app.lystproject.mobile.libraries.cache.model.CatDetailCacheModel
import app.lystproject.mobile.libraries.testutils.ERROR_MSG
import java.net.SocketTimeoutException

internal object DummyData {
    const val name = "Abyssinian"
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

    val cacheModel = CatCacheModel(
        "Abyssinian Green",
        "abys2",
        "0XYvRd7oD",
        "Egypt",
        "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
    )

    val catList: List<CatModel> = listOf(cat)

    const val query = "Abyssinian"

    val catDetail = CatDetailModel(
        "",
        "abys",
        "Abyssinian",
        "Egypt",
        "0XYvRd7oD",
        5F,
        "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
    )
    val catDetailCache = CatDetailCacheModel(
        "",
        "abys",
        "Abyssinian",
        "Egypt",
        "0XYvRd7oD",
        5F,
        "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
    )

    val exception: SocketTimeoutException
        get() = SocketTimeoutException(ERROR_MSG)
}
