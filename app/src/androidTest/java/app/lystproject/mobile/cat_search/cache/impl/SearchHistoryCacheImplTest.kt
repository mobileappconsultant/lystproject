package app.lystproject.mobile.cat_search.cache.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.lystproject.mobile.cat_search.lib.data.contract.cache.SearchHistoryCache
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.ui.DummyData
import app.lystproject.mobile.libraries.cache.impl.SearchHistoryCacheImpl
import app.lystproject.mobile.libraries.cache.mapper.CatModelMapper
import app.lystproject.mobile.libraries.cache.room.TroubleCatsDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchHistoryCacheImplTest {

    private lateinit var searchHistoryCache: SearchHistoryCache
    private lateinit var troubleCatsDatabase: TroubleCatsDatabase


    @Before
    fun setup() {
        troubleCatsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TroubleCatsDatabase::class.java
        ).allowMainThreadQueries().build()

        searchHistoryCache =
            SearchHistoryCacheImpl(
                troubleCatsDatabase.searchHistoryDao,
                troubleCatsDatabase.catDetailDao,
                CatModelMapper()
            )
    }

    @Test
    fun checkThatSaveSearchInsertsDataIntoDatabase() = runBlocking {
        val cat: CatModel = DummyData.cat

        searchHistoryCache.saveSearch(cat)

        val result: CatModel = searchHistoryCache.getSearchHistory().first()
        assertThat(cat.name).isEqualTo(result.name)
        assertThat(cat.id).isEqualTo(result.id)
        assertThat(cat.imageUrl).isEqualTo(result.imageUrl)
        assertThat(cat.origin).isEqualTo(result.origin)
        assertThat(cat.reference_image_id).isEqualTo(result.reference_image_id)
    }

    @Test
    fun checkThatGetSearchHistoryReturnsTheLastSavedSearchFirst() = runBlocking {
        val cat: CatModel = DummyData.cat2
        val cat2: CatModel = DummyData.cat2.copy(imageUrl = "https://cdn2.thecatapi.com/images/0XYvRd7o4.jpg")
        val cat3: CatModel = DummyData.cat2.copy(imageUrl = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg")

        searchHistoryCache.saveSearch(cat)
        searchHistoryCache.saveSearch(cat2)
        searchHistoryCache.saveSearch(cat3)

        val result: CatModel = searchHistoryCache.getSearchHistory().first()
        assertThat(result).isEqualTo(cat3)
    }

    @Test
    fun checkThatSaveSearchReplacesAlreadySavedItem() = runBlocking {
        val cat: CatModel = DummyData.cat
        val name = "American Shorthair"
        val cat1: CatModel = DummyData.cat.copy(name = name)

        searchHistoryCache.saveSearch(cat)
        searchHistoryCache.saveSearch(cat1)

        val result: CatModel =
            searchHistoryCache.getSearchHistory().first()
        assertThat(result).isNotEqualTo(cat)
        assertThat(result.name).isEqualTo(name)
    }

    @Test
    fun checkThatGetSearchHistoryReturnsDataInDescendingOrder() = runBlocking {
        val cat: CatModel = DummyData.cat
        val cat2: CatModel = DummyData.cat.copy(id = "0XYvRd7o4")
        val cat3: CatModel = DummyData.cat.copy(id = "0XYvRd7o5")

        searchHistoryCache.saveSearch(cat)
        searchHistoryCache.saveSearch(cat2)
        searchHistoryCache.saveSearch(cat3)

        val allItems: List<CatModel> = listOf(cat3, cat2, cat)
        val result: List<CatModel> = searchHistoryCache.getSearchHistory()
        assertThat(allItems).containsExactlyElementsIn(result).inOrder()
    }

    @Test
    fun checkThatClearSearchHistoryClearsAllSavedSearches() = runBlocking {
        val cat: CatModel = DummyData.cat
        val cat3: CatModel = DummyData.cat.copy(imageUrl = "https://cdn2.thecatapi.com/images/0XYvRd7o4.jpg")

        searchHistoryCache.saveSearch(cat)
        searchHistoryCache.saveSearch(cat3)

        searchHistoryCache.clearSearchHistory()

        val result: List<CatModel> = searchHistoryCache.getSearchHistory()
        assertThat(result).isEmpty()
    }

    @After
    fun tearDown() {
        troubleCatsDatabase.close()
    }
}
