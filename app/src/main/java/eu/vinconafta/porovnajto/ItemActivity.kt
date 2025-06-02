package eu.vinconafta.porovnajto

import android.app.Activity
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.vinconafta.porovnajto.datas.Item
import eu.vinconafta.porovnajto.ui.Rooms.AppDatabase

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



    @Composable
    fun ShowDetailItem(item: Item, modifier: Modifier = Modifier) {
        val context = LocalContext.current
        val priceDao = remember { AppDatabase.getDatabase(context).priceDao() }

        // produceState spustí coroutine na pozadí a spravuje stav ceny
//        val priceState = produceState<Price?>(initialValue = null, item.refToPrice) {
//            value = priceDao.getById(item.refToPrice)
//        }

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
                            painter = painterResource(id = item.getResId(LocalContext.current)),
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
                            text = "${stringResource(id = R.string.producer)}: ${item.producer}",
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
                        text = stringResource(id = R.string.best_offer) +" :",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
//                    Text(
//                        text = priceState.value?.let { "\t ${it.price} (€)" } ?: "\t Načítavam..."
//                    )
                    Text(
                        text = stringResource(id = R.string.other_offers),
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

//        Column(modifier = Modifier) {
//            MainTopAppBar(
//                onBack = {
//                    val intent = Intent(context, MainActivity::class.java)
//                    context.startActivity(intent)
//                    activity?.finish()
//                }
//            )
//            ShowDetailItem(item)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ItemPreview() {
//        ItemPage(item = Item(name = "Horalky", producer = "Sedita", icon = "horalky", refToCategory = 1, refToPrice = 1, refToStoreItem = 1))
//    }
}
