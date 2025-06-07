package eu.vinconafta.porovnajto.ui.components.menus

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import eu.vinconafta.porovnajto.R

/**
 * Súbor popisujúci vedlajšie menu s možnosťou návratu
 * @author Marek Štefanča
 */

/**
 * Funkcia slúžiaca na vykreslenie menu
 * @param modifier referencia na modifier
 * @param onBack definované spravanie po vrátení sa naspäť
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherTopAppBar(modifier: Modifier = Modifier, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name), color = Color.White)
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}