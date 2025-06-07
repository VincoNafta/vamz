package eu.vinconafta.porovnajto.mvvms

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.vinconafta.porovnajto.ui.TopBarSection
import eu.vinconafta.porovnajto.datas.Rooms.AppDatabase
import eu.vinconafta.porovnajto.datas.entities.Category
import eu.vinconafta.porovnajto.datas.entities.Item
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * Trieda slúžiaca na MVVM pre horné menu
 */
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

    /**
     * Setter ktorý mení stav toho či je sa zobrazuje hlavné alebo vedlajšie menu
     */
    fun setIsMain(value: Boolean) {
        _isMain.value = value
    }

    /**
     * Setter vybraného menu
     */
    fun selectSection(section: TopBarSection) {
        selectedSection = section
    }

    /**
     * Funkcia ktorá slúži na výber všetkých predmetov v rámci kategorie
     */
    fun getItemsInCategory(category: Int): Flow<List<Item>> {
        return db.itemDao().getAllInCategory(category)
    }

    /**
     * Funkcia ktorá kategorie podľa vybraného obchodu
     */
    fun getCategoriesInStore(storeId: Int): Flow<List<Category>>{
        return db.itemDao().getCategoriesInStore(storeId)
    }
}

