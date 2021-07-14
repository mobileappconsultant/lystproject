package app.lystproject.mobile.libraries.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.lystproject.mobile.libraries.cache.model.CatCacheModel
import app.lystproject.mobile.libraries.cache.model.CatDetailCacheModel

@Database(
    entities = [CatCacheModel::class, CatDetailCacheModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class TroubleCatsDatabase : RoomDatabase() {

    abstract val searchHistoryDao: SearchHistoryDao

    abstract val catDetailDao: CatDetailDao

    companion object {
        private const val DATABASE_NAME: String = "trouble_cats_db"
        fun build(context: Context): TroubleCatsDatabase = Room.databaseBuilder(
            context.applicationContext,
            TroubleCatsDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
