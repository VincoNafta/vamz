package eu.vinconafta.porovnajto.ui.components

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import eu.vinconafta.porovnajto.ItemActivity
import eu.vinconafta.porovnajto.datas.Item

@Composable
fun ProductList(items: List<Item>, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LazyColumn(modifier = modifier,
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
                        val intent = Intent(context, ItemActivity::class.java).apply {
                            putExtra("item", item)
                        }
                        context.startActivity(intent)
                    }
                    .padding(8.dp)
            )
        }
    }
}