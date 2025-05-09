package eu.vinconafta.porovnajto.mvvms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.vinconafta.porovnajto.TopBarSection

class TopBarViewModel : ViewModel() {
    var selectedSection by mutableStateOf(TopBarSection.Obchody) // ← predvolená hodnota
        private set

    fun selectSection(section: TopBarSection) {
        selectedSection = section
    }
}
