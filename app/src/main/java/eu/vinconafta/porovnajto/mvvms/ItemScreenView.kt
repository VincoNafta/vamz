package eu.vinconafta.porovnajto.mvvms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.vinconafta.porovnajto.datas.Category
import eu.vinconafta.porovnajto.datas.Currency
import eu.vinconafta.porovnajto.datas.Item
import eu.vinconafta.porovnajto.datas.Price
import eu.vinconafta.porovnajto.ui.Rooms.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ItemScreenView(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)



    fun getItemById(id: Int): Flow<Item?> {
        return db.itemDao().getId(id)
    }

    fun getPrice(id: Int): Flow<Price?> {
        return db.priceDao().getById(id)
    }

    fun getCurrency(id: Int): Flow<Currency?> {
        return db.currencyDao().genOnce(id)
    }
}