package eu.vinconafta.porovnajto.mvvms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import eu.vinconafta.porovnajto.datas.entities.Currency
import eu.vinconafta.porovnajto.datas.entities.Item
import eu.vinconafta.porovnajto.datas.entities.Price
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import eu.vinconafta.porovnajto.ui.Rooms.AppDatabase
import kotlinx.coroutines.flow.Flow

class ItemScreenView(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)



    fun getItemById(id: Int): Flow<Item?> {
        return db.itemDao().getId(id)
    }

    fun getBestPrice(id: Int): Flow<Price?> {
        return db.priceDao().getBestPrice(id)
    }

    fun getCurrency(id: Int): Flow<Currency?> {
        return db.currencyDao().genOnce(id)
    }

    fun getItemStore(itemId: Int, priceId:Int): Flow<StoreItem?>{
        return db.priceDao().getStoreByPriceItem(priceId, itemId)
    }
}