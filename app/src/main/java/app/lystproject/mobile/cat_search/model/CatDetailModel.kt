package app.lystproject.mobile.cat_search.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatDetailModel(
    val imageUrl: String?,
    val id: String,
    val name: String = "",
    val origin: String = "",
    val reference_image_id: String?,
    val energy_level: Float? = 0F,
    val description: String = "",
    val wikipedia_url: String = ""
) : Parcelable