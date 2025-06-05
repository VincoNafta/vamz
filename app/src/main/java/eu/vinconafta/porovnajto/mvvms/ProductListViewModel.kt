package eu.vinconafta.porovnajto.mvvms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.vinconafta.porovnajto.datas.entities.Category
import eu.vinconafta.porovnajto.datas.entities.Item
import eu.vinconafta.porovnajto.ui.Rooms.AppDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ProductListViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)

    val items: StateFlow<List<Item>> = db.itemDao()
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}