package eu.vinconafta.porovnajto.datas.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.Price
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.selects.select

@Dao
interface PriceDao {
    @Query("SELECT * FROM price")
    fun getAll(): Flow<List<Price>>

    @Query("SELECT * FROM price where id = :priceId")
    suspend fun getById(priceId: Int): Price?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(price: Price)

    @Delete
    suspend fun delete(price: Price)

    @Query("DELETE FROM price")
    suspend fun deleteAll()
}