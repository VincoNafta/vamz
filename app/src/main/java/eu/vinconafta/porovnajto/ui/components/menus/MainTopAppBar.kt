package eu.vinconafta.porovnajto.ui.components.menus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import eu.vinconafta.porovnajto.R
import eu.vinconafta.porovnajto.mvvms.TopBarViewModel
import eu.vinconafta.porovnajto.ui.TopBarSection

/**
 * Súbor ktorý popisuje zobrazenie hlavného menu
 * @author Marek Štefanča
 */


/**
 * Metoda služiaca na vykreslenie hlavného menu (baru)
 * @param viewModel referencia na viewModel
 * @param navController referencia na navController
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(viewModel: TopBarViewModel = viewModel(), navController: NavHostController) {
    val selected = viewModel.selectedSection

    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 25.sp,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopBarSection.values().forEach { section ->
                    TopBarButton(section, selected) {
                        viewModel.selectSection(section)
                        navController.navigate(section.name) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
    }
}

/**
 * Metoda služiaca na zobrazenie tlačidla a ak je vybrate tak ho podčiarke a zmení farbu
 * @param section konkretna položka/sekcia
 * @param selected vybrana sekcia
 * @param onClick definované správanie po kliknutí
 */
@Composable
fun TopBarButton(
    section: TopBarSection,
    selected: TopBarSection,
    onClick: (TopBarSection) -> Unit
) {
    val isSelected = section == selected
    TextButton(onClick = { onClick(section) }) {
        Text(
            stringResource(id = section.categoryName),
            color = if (isSelected) Color.Red else Color.White,
            textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None
        )
    }
}