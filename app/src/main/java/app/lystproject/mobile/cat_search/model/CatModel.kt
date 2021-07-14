package app.lystproject.mobile.cat_search.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatModel(
    val name: String,
    val id: String,
    val reference_image_id: String?,
    val origin: String?,
    var imageUrl: String?
) : Parcelable
