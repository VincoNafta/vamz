package eu.vinconafta.porovnajto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.vinconafta.porovnajto.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { MainTopBar() }, // Pridáme TopAppBar na vrch
                        content = { paddingValues ->
                            val sampleCards = listOf(
                                CardItem(R.drawable.kaufland, "Kaufland"),
                                CardItem(R.drawable.tesco, "Tesco"),
                                CardItem(R.drawable.billa, "BILLA"),
                                CardItem(R.drawable.lidl, "LIDL")
                            )
                            // Aplikujeme padding na obsah, aby sa neprekrýval s lištou
                            CardGrid(
                                cardItems = sampleCards,
                                modifier = Modifier.padding(paddingValues) // Opravené tu!
                            )
                        }
                    )
                }
            }
        }
    }
}



data class CardItem(val imageRes: Int, val text: String)


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
fun CardWithImageAndText(item: CardItem) {
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
                painter = painterResource(id = item.imageRes),
                contentDescription = item.text,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(8.dp)
            )
            Text(
                text = item.text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun CardGrid(cardItems: List<CardItem>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 karty vedľa seba
        modifier = modifier
            .fillMaxSize() // Aby grid vyplnil celú šírku a výšku
            .padding(8.dp), // Aplikuje padding na celý grid
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
    val sampleCards = listOf(
        CardItem(R.drawable.ic_launcher_background, "Karta 1"),
        CardItem(R.drawable.ic_launcher_background, "Karta 2"),
        CardItem(R.drawable.ic_launcher_background, "Karta 3"),
        CardItem(R.drawable.ic_launcher_background, "Karta 4"),
        CardItem(R.drawable.ic_launcher_background, "Karta 5")
    )
    CardGrid(cardItems = sampleCards)
}
