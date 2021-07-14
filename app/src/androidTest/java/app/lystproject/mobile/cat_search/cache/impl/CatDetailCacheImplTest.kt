package app.lystproject.mobile.cat_search.cache.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.lystproject.mobile.cat_search.lib.data.contract.cache.CatDetailCache
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.cat_search.ui.DummyData
import app.lystproject.mobile.libraries.cache.impl.CatDetailCacheImpl
import app.lystproject.mobile.libraries.cache.mapper.CatDetailCacheMapper
import app.lystproject.mobile.libraries.cache.room.TroubleCatsDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatDetailCacheImplTest {

    private lateinit var cache: CatDetailCache
    private lateinit var troubleCatsDatabase: TroubleCatsDatabase

    @Before
    fun setup() {
        troubleCatsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TroubleCatsDatabase::class.java
        ).allowMainThreadQueries().build()

        cache =
            CatDetailCacheImpl(
                troubleCatsDatabase.catDetailDao,
                CatDetailCacheMapper()
            )
    }

    @Test
    fun checkThatSaveCatInsertsDataIntoDatabase() = runBlocking {
        val cat = DummyData.catDetail
        cache.saveCat(cat)

        val result: CatDetailModel? = cache.fetchCat(cat.id)
        assertThat(result).isNotNull()
    }

    @Test
    fun checkThatFetchCatReturnsData() = runBlocking {
        val cat: CatDetailModel = DummyData.catDetail

        cache.saveCat(cat)

        val result: CatDetailModel? = cache.fetchCat(cat.id)
        assertThat(result).isNotNull()
        assertThat(result?.imageUrl).isEqualTo(cat.imageUrl)
    }

    @Test
    fun checkThatFetchCatReturnsNullDataIfDatabaseIsEmpty() = runBlocking {
        val cat: CatDetailModel = DummyData.catDetail
        val result: CatDetailModel? = cache.fetchCat(cat.id)
        assertThat(result).isNull()
    }

    @After
    fun tearDown() {
        troubleCatsDatabase.close()
    }
}
