package eu.vinconafta.porovnajto.ui.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.ui.datas.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getAll(): Flow<List<Currency>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency)

    @Delete
    suspend fun delete(currency: Currency)

    @Query("DELETE FROM currency")
    suspend fun deleteAll()
}