package eu.vinconafta.porovnajto.datas.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import kotlinx.coroutines.flow.Flow

/**
 * Rozhranie popisujuce akcie nad DB pre ORM mapovanie s entitou StoreItem
 *  @author Marek Štefanča
 */
@Dao
interface StoreDao {
    /**
     * Funkcia ktorá vráti kolekciu všetkých obchodov
     * @return Kolekcia obchodov
     */
    @Query("SELECT * FROM store")
    fun getAll(): Flow<List<StoreItem>>

    /**
     * Funkcia ktorá slúži na vloženie obchodu do DB
     * @param storeItem referencia na obchod ktorý chceme vložiť
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(storeItem: StoreItem)

    /**
     * Funkcia ktorá ktorá slúži na zmazanie predmetu z DB
     * @param storeItem referencia na obchod
     */
    @Delete
    suspend fun delete(storeItem: StoreItem)

}