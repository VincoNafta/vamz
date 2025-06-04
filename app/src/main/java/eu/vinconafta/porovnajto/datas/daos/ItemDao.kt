package eu.vinconafta.porovnajto.datas.daos


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.entities.Category
import eu.vinconafta.porovnajto.datas.entities.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<Item>>

    @Query("SELECT * FROM items where refToCategory = :categoryId")
    fun getAllInCategory(categoryId: Int) : Flow<List<Item>>

    @Query("select c.* from category c join items i on (i.refToCategory = c.Id) where i.refToStoreItem = :storeId group by c.id")
    fun getCategoriesInStore(storeId: Int): Flow<List<Category>>

    @Query("SELECT * FROM items where id = :itemId")
    fun getId(itemId: Int): Flow<Item?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}