package eu.vinconafta.porovnajto.datas.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import kotlinx.coroutines.flow.Flow


@Dao
interface StoreDao {
    @Query("SELECT * FROM store")
    fun getAll(): Flow<List<StoreItem>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(storeItem: StoreItem)

    @Delete
    suspend fun delete(storeItem: StoreItem)

    @Query("DELETE FROM store")
    suspend fun deleteAll()
}