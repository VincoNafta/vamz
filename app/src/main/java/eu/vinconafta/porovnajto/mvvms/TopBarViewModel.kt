package eu.vinconafta.porovnajto.mvvms

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.vinconafta.porovnajto.ui.TopBarSection
import eu.vinconafta.porovnajto.ui.Rooms.AppDatabase
import eu.vinconafta.porovnajto.datas.Category
import eu.vinconafta.porovnajto.datas.Item
import eu.vinconafta.porovnajto.datas.StoreItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class TopBarViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)

    private val _isMain = MutableStateFlow(true)
    val isMain = _isMain.asStateFlow()

    val stores: StateFlow<List<StoreItem>> = db.storeDao()
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val categories: StateFlow<List<Category>> = db.categoryDao()
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val items: StateFlow<List<Item>> = db.itemDao()
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    var selectedSection by mutableStateOf(TopBarSection.STORES)
        private set

    fun setIsMain(value: Boolean) {
        _isMain.value = value
    }

    fun selectSection(section: TopBarSection) {
        selectedSection = section
    }

    fun getItemsInCategory(category: Int) : StateFlow<List<Item>>{
        return db.itemDao()
            .getAllInCategory(category)
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())


    }
}

