package eu.vinconafta.porovnajto.datas.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.entities.Currency
import kotlinx.coroutines.flow.Flow

/**
 * Rozhranie popisujuce akcie nad DB pre ORM mapovanie s entitou Currency
 *  @author Marek Štefanča
 */
@Dao
interface CurrencyDao {
    /**
     * Funkcia ktorá vráti zoznam všetkých mien
     * @return zoznam všetkých mien
     */
    @Query("SELECT * FROM currency")
    fun getAll(): Flow<List<Currency>>

    /**
     * Funkcia ktorá vráti pravdepodobne 1 záznam podľa id
     * @param currencyId referencia na MENU  v DB
     * @return 1 mena alebo NULL
     */
    @Query("SELECT * FROM currency where Id= :currencyId")
    fun genOnce(currencyId: Int) : Flow<Currency?>

    /**
     * Funkcia slúžiaca na vloženie meny do DB
     * @param currency referencia na menu
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency)

    /**
     * Funkcia slúžiaca na zmazanie meny z DB
     * @param currency referencia na menu
     */
    @Delete
    suspend fun delete(currency: Currency)

}