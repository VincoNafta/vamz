package eu.vinconafta.porovnajto.mvvms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import eu.vinconafta.porovnajto.ui.states.InsertStoreFormUiStatement
import kotlinx.coroutines.flow.MutableStateFlow

class FormStoreViewModel {

    private val _uiState = MutableStateFlow(InsertStoreFormUiStatement())
//    val uiState: StateFlow<InsertItemFormUiState> = _uiState.asStateFlow()
    var formState by mutableStateOf(InsertStoreFormUiStatement())

    fun updateStoreName(newStoreName:String) {
        formState = formState.copy(storeName =  newStoreName)
    }
}