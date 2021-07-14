package app.lystproject.mobile.libraries.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCH_HISTORY")
data class CatCacheModel(
    val name: String,
    @PrimaryKey
    val id: String,
    val origin: String?,
    val reference_image_id: String?,
    var imageUrl: String?
) {
    var lastUpdated: Long = 0L
}
