package eu.vinconafta.porovnajto

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.vinconafta.porovnajto.ui.datas.Category
import eu.vinconafta.porovnajto.ui.datas.Item
import eu.vinconafta.porovnajto.ui.datas.StoreItem
import eu.vinconafta.porovnajto.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
//            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Scaffold(
//                        topBar = { MainTopBar() },
//                        content = {
//                            paddingValues ->
//                            val sampleCards = listOf(
//                                StoreItem(R.drawable.kaufland, "Kaufland"),
//                                StoreItem(R.drawable.tesco, "Tesco"),
//                                StoreItem(R.drawable.billa, "BILLA"),
//                                StoreItem(R.drawable.lidl, "LIDL")
//                            )
//                            CardGrid(
//                                cardItems = sampleCards,
//                                modifier = Modifier.padding(paddingValues)
//                            )
//                        }
//                    )
//                }
//            }
        }
    }
}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    Column {
        // Hlavná horná lišta
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

        // "Druhá lišta" s nižšou výškou
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // Nastavená nižšia výška
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { /* TODO: hento klik */ }) {
                    Text("KATEGORIE", color = Color.White)
                }
                TextButton(onClick = { /* TODO: tamto klik */ }) {
                    Text("ZOZNAMY", color = Color.White)
                }
                TextButton(onClick = { /* TODO: tamto klik */ }) {
                    Text("OBCHODY",
                        color = Color.Red
//                        textDecoration = TextDecoration.Underline.
                    )
                }
                TextButton(onClick = { /* TODO: tamto klik */ }) {
                    Text("PRODUKTY", color = Color.White)
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardWithImageAndText(item: StoreItem) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = { }
    ) {
        Column {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = item.storeName,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(8.dp)
            )
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

    Column {
        items.forEach { item ->
            ListItem(headlineContent = {
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

    Column {
        categoryList.forEach { category ->
            ListItem(headlineContent = {
                Text(text = category.name)
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
    val categories = listOf(
        Category("Pečivo"),
        Category("Drogeria"),
        Category("Alkohol"),
        Category("Ovocie"),
        Category("Zelenina"),
        Category("Nealkoholické Nápoje"),
        Category("Trvanlivé potraviny"),
        Category("Mliečne výrobky")
    )
    CategoryList(categoryList = categories)
//    val sampleCards = listOf(
//        StoreItem(R.drawable.ic_launcher_background, "Karta 1"),
//        StoreItem(R.drawable.ic_launcher_background, "Karta 2"),
//        StoreItem(R.drawable.ic_launcher_background, "Karta 3"),
//        StoreItem(R.drawable.ic_launcher_background, "Karta 4"),
//        StoreItem(R.drawable.ic_launcher_background, "Karta 5")
//    )
//    CardGrid(cardItems = sampleCards)
}
