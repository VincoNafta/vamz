package eu.vinconafta.porovnajto.datas.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.entities.Item
import eu.vinconafta.porovnajto.datas.entities.ItemStorePrice
import eu.vinconafta.porovnajto.datas.entities.Price
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import kotlinx.coroutines.flow.Flow


/**
 * Rozhranie popisujuce akcie nad DB pre ORM mapovanie s entitou Price
 *  @author Marek Štefanča
 */
@Dao
interface PriceDao {
    /**
     * Funkcia ktorá vráti celý zoznam predmetov
     * @return zoznam všetkých predmetov
     */
    @Query("SELECT * FROM price")
    fun getAll(): Flow<List<Price>>

    /**
     * Funkcia ktorá vráti pravdepodobne 1 cenu
     * @param priceId referencia na DB id pre entitu ceny
     * @return 1 záznam ceny alebo NULL
     */
    @Query("SELECT * FROM price where id = :priceId")
    fun getById(priceId: Int): Flow<Price?>

    /**
     * Funkcia ktorá vráti referenciu na pravdepodobne minimálnu cenu
     * @param itemId referencia na itemId
     * @return 1 záznam alebo NULL
     */
    @Query("select p.* from items i " +
            "join itemstoreprice isp on (isp.refToItem = i.id) " +
            "join price p  on (p.id = isp.refToPrice) " +
            "where i.id = :itemId " +
            "group by p.id " +
            "having p.price = min(p.price)" +
            "limit 1")
    fun getBestPrice(itemId: Int): Flow<Price?>

    /**
     * Funkcia ktorá vracia pravdepodobne 1 záznam z referenčnej tabuľky ItemStorePrice
     * @param itemId referencia na DB ItemId
     */
    @Query("SELECT st.* FROM ItemStorePrice isp " +
            "join store st on (st.id = isp.refToStore)" +
            "where isp.refToPrice = :priceId and isp.refToItem = :itemId")
    fun getStoreByPriceItem(priceId: Int, itemId: Int): Flow<StoreItem?>

    /**
     * Funkcia ktorá slúži na vloženie záznamu do referenčnej tabuľky ItemStorePrice
     * @param storePrice referencia na ItemStorePrice
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStorePrice(storePrice: ItemStorePrice)

    /**
     * Funkcia ktorá slúži na vloženie Ceny do tabuľky Price
     * @return vráti Id po vložení
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(price: Price): Long

    /**
     * Funkcia ktorá slúži na zmazanie danej ceny
     * @param referencia na cenu
     */
    @Delete
    suspend fun delete(price: Price)

}