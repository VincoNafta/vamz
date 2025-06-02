package eu.vinconafta.porovnajto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.vinconafta.porovnajto.mvvms.ItemScreenView





@Composable
fun ItemScreen(
    itemId: Int,
    viewModel: ItemScreenView = viewModel(),
    modifier: Modifier = Modifier
) {
    val itemFlow = remember(itemId) { viewModel.getItemById(itemId) }
    val itemState by itemFlow.collectAsState(initial = null)

    if (itemState == null) {
        Text(
            text = "Načítavam položku...",
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 18.sp
        )
        return
    }

    val item = itemState!!

    val priceFlow = remember(item.refToPrice) { viewModel.getPrice(item.refToPrice) }
    val priceState by priceFlow.collectAsState(initial = null)

    if (priceState == null) {
        Text(
            text = "Načítavam cenu...",
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 18.sp
        )
        return
    }

    val price = priceState!!

    val currencyFlow = remember(price.currencyId) { viewModel.getCurrency(price.currencyId) }
    val currencyState by currencyFlow.collectAsState(initial = null)

    if (currencyState == null) {
        Text(
            text = "Načítavam menu...",
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 18.sp
        )
        return
    }

    val currency = currencyState!!

    // Všetky dáta sú načítané, môžeme zobraziť UI
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
                    text = stringResource(id = R.string.best_offer) + " :",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(
                    text = "${price.price} ${currency.symbol}"
                )
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







//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//        Text(text = item.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
//        Text(text = "Manufacturer: ${item.producer}", fontSize = 18.sp)
//    }
}