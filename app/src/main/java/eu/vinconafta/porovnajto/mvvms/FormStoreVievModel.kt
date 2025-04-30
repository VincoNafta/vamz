package eu.vinconafta.porovnajto.mvvms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import eu.vinconafta.porovnajto.ui.states.InsertItemFormUiState
import eu.vinconafta.porovnajto.ui.states.InsertStoreFormUiStatement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormStoreVievModel {

    private val _uiState = MutableStateFlow(InsertStoreFormUiStatement())
//    val uiState: StateFlow<InsertItemFormUiState> = _uiState.asStateFlow()
    var formState by mutableStateOf(InsertStoreFormUiStatement())
}