package eu.vinconafta.porovnajto.datas.daos


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.entities.Category
import eu.vinconafta.porovnajto.datas.entities.Item
import kotlinx.coroutines.flow.Flow

/**
 * Rozhranie popisujuce akcie nad DB pre ORM mapovanie s entitou Item (položka)
 *  @author Marek Štefanča
 */
@Dao
interface ItemDao {

    /**
     * Funkcia ktorá vráti všetky predmety
     * @return zoznam predmetov
     */
    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<Item>>

    /**
     * funkcia slúži na vrátenie všetkých predmetov v danej kategoriri
     * @return
     */
    @Query("SELECT * FROM items where refToCategory = :categoryId")
    fun getAllInCategory(categoryId: Int) : Flow<List<Item>>

    @Query("SELECT c.* FROM STORE s " +
            "join ItemStorePrice isp on (isp.refToStore = s.id) " +
            "join items i on i.id = isp.refToItem " +
            "join category c on c.id = i.refToCategory " +
            "where s.id = :storeId " +
            "group by c.id")
    fun getCategoriesInStore(storeId: Int): Flow<List<Category>>

    @Query("SELECT * FROM items where id = :itemId")
    fun getId(itemId: Int): Flow<Item?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item): Long

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}