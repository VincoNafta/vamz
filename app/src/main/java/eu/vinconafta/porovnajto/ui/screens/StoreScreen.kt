package eu.vinconafta.porovnajto.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.vinconafta.porovnajto.datas.entities.StoreItem
import eu.vinconafta.porovnajto.R

/**
 * Obrazovka kde su zobrazené obchody
 *  @author Marek Štefanča
 */

/**
 * Metoda ktora zobrazi jednu kartu s možnosťou kliknuť
 * @param item referencia na obchod
 * @param navController referencia na navConroller
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardStore(item: StoreItem, navController: NavController) {
    val context = LocalContext.current
    val resId = remember(item.iconName) { item.getResId(context) }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = {navController.navigate("storeCategory/${item.id}")}
    ) {
        Column {
            if (resId != 0) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = item.storeName,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .padding(8.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(id = R.string.noImage), color = Color.Gray)
                }
            }

            Text(
                text = item.storeName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

/**
 * Metoda ktorá vykreslí obrazovku
 * @param cardItems kolekcia obchodov ktore sa maju zobraziť
 * @param navController referencia na navConroller
 * @param modifier referenia na modifier
 */
@Composable
fun StoreScreen(cardItems: List<StoreItem>, navController: NavController, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(cardItems.size) { index ->
            CardStore(item = cardItems[index], navController)
        }
    }
}