package app.lystproject.mobile.libraries.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CAT_DETAILS")
data class CatDetailCacheModel(
    val imageUrl: String?,
    @PrimaryKey
    val id: String,
    val name: String = "",
    val origin: String = "",
    val reference_image_id: String?,
    val energy_level: Float? = 0F,
    val description: String = "",
    val wikipedia_url: String = ""
)
