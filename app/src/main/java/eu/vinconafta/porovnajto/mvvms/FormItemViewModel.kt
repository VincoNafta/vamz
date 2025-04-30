package eu.vinconafta.porovnajto.mvvms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.vinconafta.porovnajto.ui.states.InsertItemFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormItemViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InsertItemFormUiState())
    val uiState: StateFlow<InsertItemFormUiState> = _uiState.asStateFlow()
    var formState by mutableStateOf(InsertItemFormUiState())



    fun updateStoreName(newStoreName:String) {
      formState = formState.copy(storeName =  newStoreName)
    }

    fun updateEAN(newEAN:Long) {
        formState = formState.copy(ean = newEAN)
    }

    fun updateItemName(newItemName: String) {
        formState = formState.copy(itemName = newItemName)
    }

    fun updatePrice(newPrice:Double) {
        formState = formState.copy(price = newPrice)
    }


}