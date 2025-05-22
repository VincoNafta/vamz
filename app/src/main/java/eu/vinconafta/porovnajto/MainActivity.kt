package eu.vinconafta.porovnajto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.vinconafta.porovnajto.mvvms.TopBarViewModel
import eu.vinconafta.porovnajto.ui.Rooms.AppDatabase
import eu.vinconafta.porovnajto.ui.datas.Category
import eu.vinconafta.porovnajto.ui.datas.Item
import eu.vinconafta.porovnajto.ui.datas.StoreItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val intent = Intent(this, FormActivity::class.java)
//            startActivity(intent)
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(
    topBarViewModel: TopBarViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            MainTopBar(viewModel = topBarViewModel)
        },
        content = { paddingValues ->
            val context = LocalContext.current
            when (topBarViewModel.selectedSection) {
                TopBarSection.Obchody -> {
                    val sampleCards = listOf(
                        StoreItem( "Kaufland", "kaufland"),
                        StoreItem( "Tesco", "tesco"),
                        StoreItem( "BILLA", "billa"),
                        StoreItem( "LIDL", "lidl"),
                        StoreItem( "Jednota", "jednota"),
                        StoreItem( "Terno", "terno")
                    )
                    CardGrid(
                        cardItems = sampleCards,
                        modifier = Modifier.padding(paddingValues)
                    )
                }

                TopBarSection.Kategorie -> {

                    val categoryDao = remember { AppDatabase.getDatabase(context).categoryDao() }
                    val categoryFlow = remember { categoryDao.getAllCategories() }
                    val categories by categoryFlow.collectAsState(initial = emptyList())
//                    val categories = listOf(
//                        Category("Pečivo"),
//                        Category("Drogeria"),
//                        Category("Alkohol"),
//                        Category("Ovocie"),
//                        Category("Zelenina"),
//                        Category("Nealkoholické Nápoje"),
//                        Category("Trvanlivé potraviny"),
//                        Category("Mliečne výrobky")
//                    )
                    CategoryList(categoryList = categories)
                }

                TopBarSection.Zoznamy -> {


                    // Získa DAO raz - bezpečne cez remember
                    val itemDao = remember { AppDatabase.getDatabase(context).itemDao() }
                    val itemsFlow = remember { itemDao.getAllItems() }
                    val items by itemsFlow.collectAsState(initial = emptyList())

                    ProductList(items = items)
                }

                TopBarSection.Produkty -> {
                    Text("Tu budú produkty", modifier = Modifier.padding(paddingValues))
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(viewModel: TopBarViewModel = viewModel()) {
    val selected = viewModel.selectedSection

    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Porovnaj.to",
                    fontSize = 25.sp,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        // Druhá lišta s tlačidlami
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
                TopBarButton(TopBarSection.Kategorie, selected, viewModel::selectSection)
                TopBarButton(TopBarSection.Zoznamy, selected, viewModel::selectSection)
                TopBarButton(TopBarSection.Obchody, selected, viewModel::selectSection)
                TopBarButton(TopBarSection.Produkty, selected, viewModel::selectSection)
            }
        }
    }
}



@Composable
fun TopBarButton(
    section: TopBarSection,
    selected: TopBarSection,
    onClick: (TopBarSection) -> Unit
) {
    val isSelected = section == selected
    TextButton(onClick = { onClick(section) }) {
        Text(
            stringResource(id = section.category_name),
            color = if (isSelected) Color.Red else Color.White,
            textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardWithImageAndText(item: StoreItem) {
    val context = LocalContext.current
    val resId = remember(item.iconName) { item.getResId(context) }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = { }
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
                // fallback: prázdny Box alebo iný placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No Image", color = Color.Gray)
                }
            }

            Text(
                text = item.storeName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}


@Composable
fun ProductList(items: List<Item>, modifier: Modifier = Modifier) {
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
                    .clickable { }
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun CategoryList(categoryList: List<Category>, modifier: Modifier = Modifier) {
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
                    .clickable { }
                    .padding(8.dp)
            )
        }
    }
}


@Composable
fun CardGrid(cardItems: List<StoreItem>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(cardItems.size) { index ->
            CardWithImageAndText(item = cardItems[index])
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCardGrid() {
//    val items = listOf(Item("Rožok Štandard", "Lippek"),
//        Item("Polohrubá Múka", "Mlyn Pohronoský Ruskov"),
//        Item("Chlieb Ražný", "Pekárne a cukrárne Rusina Dolný Kubín"),
//        Item("Kryšálový Cukor", "Považšký Cukor a.s."),
//        Item("Jägermeister", "Massvoll Geniessen GmbH."),
//        Item("Fusilli", "GORAL, spol. s r.o."),
//        Item("Džús Caprio Multivitamin", "Grupa Maspex Polska"),
//        Item("Minerálka Budiš", "BudiŠ a.s."),
//        Item("Plienky Pampers", "Procter&Gamble")
//    )
//    ProductList(items = items)
//    val categories = listOf(
//        Category("Pečivo"),
//        Category("Drogeria"),
//        Category("Alkohol"),
//        Category("Ovocie"),
//        Category("Zelenina"),
//        Category("Nealkoholické Nápoje"),
//        Category("Trvanlivé potraviny"),
//        Category("Mliečne výrobky")
//    )
//    CategoryList(categoryList = categories)
//    val sampleCards = listOf(
//        StoreItem(R.drawable.ic_launcher_background, "Karta 1"),
//        StoreItem(R.drawable.ic_launcher_background, "Karta 2"),
//        StoreItem(R.drawable.ic_launcher_background, "Karta 3"),
//        StoreItem(R.drawable.ic_launcher_background, "Karta 4"),
//        StoreItem(R.drawable.ic_launcher_background, "Karta 5")
//    )
//    CardGrid(cardItems = sampleCards)
}
