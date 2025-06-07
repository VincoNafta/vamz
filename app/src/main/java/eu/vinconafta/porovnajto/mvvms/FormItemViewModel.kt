package eu.vinconafta.porovnajto.mvvms

import InsertItemFormUiState
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import eu.vinconafta.porovnajto.datas.entities.Item
import eu.vinconafta.porovnajto.datas.entities.ItemStorePrice
import eu.vinconafta.porovnajto.datas.entities.Price
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import eu.vinconafta.porovnajto.ui.Rooms.AppDatabase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FormItemViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    var formState by mutableStateOf(InsertItemFormUiState())

    var priceInput by mutableStateOf("")
        private set

    val stores: StateFlow<List<StoreItem>> = db.storeDao()
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        priceInput = if (formState.price == 0.0) "" else formState.price.toString()
    }

    fun updateStoreName(newStoreName: String) {
        formState = formState.copy(storeName = newStoreName)
    }

    fun updateStoreId(newStoreId: Int) {
        formState = formState.copy(storeId = newStoreId)
    }

    fun updateItemName(newItemName: String) {
        formState = formState.copy(itemName = newItemName)
    }

    fun updateProducer(newProducer: String) {
        formState = formState.copy(producer = newProducer)
    }

    fun updatePriceInput(newPriceInput: String) {
        priceInput = newPriceInput

        val parsedPrice = newPriceInput.toDoubleOrNull()
        if (parsedPrice != null) {
            formState = formState.copy(price = parsedPrice)
        } else if (newPriceInput.isEmpty()) {
            formState = formState.copy(price = 0.0)
        }
    }

    fun registerItem(navController: NavController) {
        val priceValue = priceInput.toDoubleOrNull() ?: 0.0

        if (formState.itemName.isBlank() || priceValue <= 0 || formState.producer.isBlank() || formState.storeId <= 0) {

            return
        }

        viewModelScope.launch {
            val insertedId = db.priceDao().insert(Price(currencyId = 1, price = priceValue)).toInt()
            val newItemId = db.itemDao().insertItem(Item(name = formState.itemName, producer = formState.producer, icon = formState.itemName.lowercase(), refToCategory = 1))
            db.priceDao().insertStorePrice(ItemStorePrice(refToPrice = insertedId, refToStore = formState.storeId, refToItem = newItemId.toInt()))
        }

        navController.popBackStack()



    }
}
