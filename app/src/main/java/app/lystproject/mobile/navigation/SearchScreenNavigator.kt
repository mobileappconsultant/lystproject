package app.lystproject.mobile.navigation

import androidx.navigation.NavController
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.navigation.Navigator
import app.lystproject.mobile.cat_search.ui.SearchFragmentDirections
import javax.inject.Inject

internal class SearchScreenNavigator @Inject constructor(
    private val navController: NavController
) : Navigator {

    override fun openCatDetail(model: CatModel) {
        navController.navigate(SearchFragmentDirections.openDetail(model.toDetail()))
    }
}

internal fun CatModel.toDetail() =
    CatDetailModel(
        name = name,
        id = id,
        reference_image_id = reference_image_id,
        imageUrl = imageUrl

    )