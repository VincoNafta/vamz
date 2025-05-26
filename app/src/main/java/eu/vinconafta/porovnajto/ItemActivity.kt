package eu.vinconafta.porovnajto

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.vinconafta.porovnajto.ui.datas.Item

class ItemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val item: Item? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("item", Item::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Item>("item")
        }

        if (item == null) {
            finish()  // Ak sa item nenašiel, ukončíme aktivitu
            return
        }

        setContent {
            ItemPage(item)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(modifier: Modifier = Modifier, onBack: () -> Unit) {
        TopAppBar(
            title = {
                Text("Porovnaj.to", color = Color.White)
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
        )
    }

    @Composable
    fun ShowDetailItem(item: Item, modifier: Modifier = Modifier) {
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.horalky),
                            contentDescription = item.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                        )
                        Text(
                            text = item.name.uppercase(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "Výrobca: ${item.producer}",
                            fontSize = 15.sp,
                            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "Najvýhodnejšia ponuka:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(text = "\t LIDL (0,55€)")
                    Text(
                        text = "Ostatné ponuky",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    Text(
                        text = "- TESCO (0,69)\n" +
                                "- BILLA (0,64)\n" +
                                "- KAUFLAND (0,60€)\n"
                    )
                }
            }
        }
    }


    @Composable
    fun ItemPage(item: Item) {
        val context = LocalContext.current
        val activity = context as? Activity

        Column(modifier = Modifier) {
            TopBar(
                onBack = {
                    // Ak chceš len vrátiť späť do MainActivity:
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    activity?.finish()
                }
            )
            ShowDetailItem(item)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ItemPreview() {
        ItemPage(item = Item(name = "Horalky", producer = "Sedita", icon = "horalky", refToCategory = 1, refToPrice = 1, refToStoreItem = 1))
    }
}
