package eu.vinconafta.porovnajto.mvvms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import eu.vinconafta.porovnajto.datas.entities.Currency
import eu.vinconafta.porovnajto.datas.entities.Item
import eu.vinconafta.porovnajto.datas.entities.Price
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import eu.vinconafta.porovnajto.datas.Rooms.AppDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Trieda slúžiaca na prepojenie frontendu a backendu pre Zobrazenie ItemScreenView
 * @author Marek Štefanča
 */
class ItemScreenView(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)

    /**
     * Metoda ktorá vravcia pravdepodobne 1 zaznam na predmet podľa ID alebo NULL
     */
    fun getItemById(id: Int): Flow<Item?> {
        return db.itemDao().getId(id)
    }

    /**+
     * Metoda ktora vracia najlepšiu cenu pre dany predmet
     */
    fun getBestPrice(id: Int): Flow<Price?> {
        return db.priceDao().getBestPrice(id)
    }

    /**
     * Metoda ktora vracia Menu pre daný predmet
     */
    fun getCurrency(id: Int): Flow<Currency?> {
        return db.currencyDao().genOnce(id)
    }

    /**
     * Metoda ktorá vracia pravdepobodne 1 predmet pre kombinaciu ceny a obchodu ak nenájde vráti NULL
     */
    fun getItemStore(itemId: Int, priceId:Int): Flow<StoreItem?>{
        return db.priceDao().getStoreByPriceItem(priceId, itemId)
    }
}