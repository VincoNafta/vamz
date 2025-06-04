package eu.vinconafta.porovnajto.ui.components.lists

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
import androidx.navigation.NavController
import eu.vinconafta.porovnajto.datas.entities.Item



@Composable
fun ProductList(
    items: List<Item>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 30.dp, bottom = 8.dp)
    ) {
        items(items) { item ->
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
    }
}

