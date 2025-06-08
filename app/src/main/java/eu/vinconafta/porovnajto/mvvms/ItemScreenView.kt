package eu.vinconafta.porovnajto.mvvms

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.vinconafta.porovnajto.datas.entities.Currency
import eu.vinconafta.porovnajto.datas.entities.Item
import eu.vinconafta.porovnajto.datas.entities.Price
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import eu.vinconafta.porovnajto.datas.Rooms.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import eu.vinconafta.porovnajto.R
import java.lang.StringBuilder

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

    /**
     * Asynchronna funkcia ktorá vracia zoznam ostatných produktov ako textový reťazec
     * @param itemId referencia na itemId
     * @return textový reťazec ktorý obsahuje predmety alebo je prázdny
     */
    suspend fun getOtherOffers(itemId: Int): String {
        val sb = StringBuilder()

        val bestPrice = getBestPrice(itemId).first()
        if (bestPrice != null) {
            val references = db.priceDao().getReferences(itemId, bestPrice.id).first()

            for (reference in references) {
                val price: Price? = db.priceDao().getById(reference.refToPrice).first()
                val store: StoreItem? = db.storeDao().getOnce(reference.refToStore).first()
                val currency: Currency? = price?.let {
                    db.currencyDao().genOnce(it.currencyId).first()
                }

                if (store != null && price != null && currency != null) {
                    sb.append(" - ${store.storeName} (${price.price} ${currency.symbol})\n")
                }
            }
        }


        return sb.toString()
    }




}