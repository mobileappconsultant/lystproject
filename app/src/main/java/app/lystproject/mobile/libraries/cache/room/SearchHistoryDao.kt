package app.lystproject.mobile.libraries.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.lystproject.mobile.libraries.cache.model.CatCacheModel

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(catCacheModel: CatCacheModel)

    @Query("SELECT * FROM SEARCH_HISTORY ORDER BY lastUpdated DESC")
    suspend fun recentSearches(): List<CatCacheModel>

    @Query("DELETE FROM SEARCH_HISTORY")
    suspend fun clearHistory()
}
