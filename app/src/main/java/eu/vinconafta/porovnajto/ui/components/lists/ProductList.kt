package eu.vinconafta.porovnajto.ui.components.lists

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.vinconafta.porovnajto.datas.entities.Item

//import eu.vinconafta.porovnajto.mvvms.ProductListViewModel

/**
 * Komponent ktorý vykresluje list produktov
 *  @author Marek Štefanča
 */

/**
 * Jeden chlievik pre dany item
 * @param item referencia na item
 * @param navController referencia na navcontroller
 */
@Composable
fun ProductItem(item: Item, navController: NavController) {
    ListItem(
        headlineContent = {
            Text(text = item.name)
        },
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                navController.navigate("item/${item.id}")
            }
            .padding(8.dp)
    )
}

/**
 * Metoda ktorá vykreslí zoznam produktov podľa kolekcie
 * @param items kolekcia predmetov podľa výberu
 * @param navController rerencia na navController
 * @param modifier referencia na modifier
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductList(
    items: List<Item>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // akcia
                navController.navigate("createitem")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, bottom = 80.dp), // vlastný padding pre FAB
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(items) { item ->
                ProductItem(item = item, navController = navController)
            }
        }
    }

}


