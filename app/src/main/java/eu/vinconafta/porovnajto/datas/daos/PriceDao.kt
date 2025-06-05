package eu.vinconafta.porovnajto.datas.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.entities.Price
import kotlinx.coroutines.flow.Flow

@Dao
interface PriceDao {
    @Query("SELECT * FROM price")
    fun getAll(): Flow<List<Price>>

    @Query("SELECT * FROM price where id = :priceId")
    fun getById(priceId: Int): Flow<Price?>

    @Query("select p.* from items i " +
            "join itemstoreprice isp on (isp.refToItem = i.id) " +
            "join price p  on (p.id = isp.refToPrice) " +
            "where i.id = :itemId " +
            "group by p.id " +
            "having p.price = min(p.price)" +
            "limit 1")
    fun getBestPrice(itemId: Int): Flow<Price?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(price: Price)

    @Delete
    suspend fun delete(price: Price)

    @Query("DELETE FROM price")
    suspend fun deleteAll()
}