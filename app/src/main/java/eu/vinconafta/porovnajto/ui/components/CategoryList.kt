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
import eu.vinconafta.porovnajto.datas.Category
import eu.vinconafta.porovnajto.datas.Item
import eu.vinconafta.porovnajto.mvvms.TopBarViewModel

@Composable
fun CategoryList(categoryList: List<Category>,
                 modifier: Modifier = Modifier,
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


                    }
                    .padding(8.dp)
            )
        }
    }
}