package app.lystproject.mobile.libraries.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.lystproject.mobile.libraries.cache.model.CatDetailCacheModel

@Dao
interface CatDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(catDetailCacheModel: CatDetailCacheModel)

    @Query("SELECT * FROM CAT_DETAILS WHERE id = :catId")
    suspend fun fetchCat(catId: String): CatDetailCacheModel?

    @Query("DELETE FROM CAT_DETAILS")
    suspend fun clearData()
}
