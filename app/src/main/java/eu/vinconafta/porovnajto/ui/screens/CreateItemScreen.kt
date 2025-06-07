package eu.vinconafta.porovnajto.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import eu.vinconafta.porovnajto.R
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import eu.vinconafta.porovnajto.mvvms.FormItemViewModel


/**
 * Súbor popisujúci zobrazenie obrazovky s formulárom
 * @author Marek Štefanča
 */

/**
 * Funkcia slúžiaca na vykreslenie  obrazovky s formulárom
 * @param insertFormUi referencia na viewmodel
 * @param navController referencia na navcontroleer
 * @param modifier referencia na modifier
 */
@Composable
fun CreateItemScreen(
    insertFormUi: FormItemViewModel = viewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val formData = insertFormUi.formState
    val priceInput = insertFormUi.priceInput

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TextField(
                value = formData.itemName,
                onValueChange = { insertFormUi.updateItemName(it) },
                label = { Text(stringResource(id = R.string.itemName)) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            StoreDropdown(viewModel = insertFormUi)
        }

        item {
            TextField(
                value = formData.producer,
                onValueChange = { insertFormUi.updateProducer(it) },
                label = { Text(stringResource(id = R.string.producer)) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            TextField(
                value = priceInput,
                onValueChange = { insertFormUi.updatePriceInput(it) },
                label = { Text(stringResource(id = R.string.price)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { insertFormUi.registerItem(navController = navController) }
                ) {
                    Text(text = stringResource(id = R.string.createBtnText))
                }
            }
        }

    }
}


/**
 * Komponent ktorý zobrazuje dropdown menu, na vytvorenie tohto dokumentu pomohol ujo chat
 * @param viewModel referencia na viewmodel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreDropdown(viewModel: FormItemViewModel) {
    val stores by viewModel.stores.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedStore by remember { mutableStateOf<StoreItem?>(null) }

    if (stores.isEmpty()) {
        Text(stringResource(id = R.string.noStores), modifier = Modifier.fillMaxWidth())
        return
    }

    if (selectedStore == null) {
        selectedStore = stores.first()
        viewModel.updateStoreName(selectedStore!!.storeName)
        viewModel.updateStoreId(selectedStore!!.id)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedStore!!.storeName,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(id = R.string.storeName)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            stores.forEach { store ->
                DropdownMenuItem(
                    text = { Text(store.storeName) },
                    onClick = {
                        selectedStore = store
                        viewModel.updateStoreName(store.storeName)
                        viewModel.updateStoreId(store.id)
                        expanded = false
                    }
                )
            }
        }
    }
}
