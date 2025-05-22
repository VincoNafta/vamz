package eu.vinconafta.porovnajto.ui.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.ui.datas.Price
import kotlinx.coroutines.flow.Flow

@Dao
interface PriceDao {
    @Query("SELECT * FROM price")
    fun getAll(): Flow<List<Price>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(price: Price)

    @Delete
    suspend fun delete(price: Price)

    @Query("DELETE FROM price")
    suspend fun deleteAll()
}