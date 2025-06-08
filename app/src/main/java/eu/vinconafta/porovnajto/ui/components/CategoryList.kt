package eu.vinconafta.porovnajto.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import eu.vinconafta.porovnajto.datas.entities.Category
import eu.vinconafta.porovnajto.mvvms.TopBarViewModel

/**
 * Súbor ktorý popisuje zobrazenie kategorii
 * @author Marek Štefanča
 */

/**
 * funkcia ktorá slúži na vykreslenie kategorii z výberu
 * @param categoryList referencia na zoznam kategorii z vyberu
 * @param navController referencia na navController
 * @param topBarViewModel referencia na viewmodel (menu)
 */
@Composable
fun CategoryList(categoryList: List<Category>,
                 modifier: Modifier = Modifier,
                 navController: NavController,
                 topBarViewModel: TopBarViewModel = viewModel()
) {



    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 16.dp, bottom = 8.dp)
    ) {
        items(categoryList) { category ->
            ListItem(
                headlineContent = {
                    Text(text = category.name)
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        navController.navigate("category/${category.id}")

                    }
                    .padding(8.dp)
            )
        }
    }
}