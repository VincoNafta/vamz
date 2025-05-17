package eu.vinconafta.porovnajto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import eu.vinconafta.porovnajto.ui.datas.Item


class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(modifier: Modifier = Modifier) {
        TopAppBar(
            title = {
                Text("Porovnaj.to", color = Color.White) // Názov aplikácie
            },
            navigationIcon = {
                IconButton(onClick = { /* Akcia pre späť */ }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }

    @Composable
    fun ShowDetailItem(item: Item,modifier: Modifier = Modifier) {
        Column {


            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.horalky),
                        contentDescription = item.name,
                        modifier = modifier.height(240.dp)
                    )
                    Text(
                        text = item.name.uppercase(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Výrobca: ${item.producer}" ,
                        fontSize = 15.sp
                    )
                }
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Bottom
            )
            {
                Text(
                    text = "Najvýhodnejšia ponuka:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(text = "\t LIDL (0,55€)")
                Text(
                    text = "Ostatné ponuky",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(
                    text = "- TESCO (0,69)\n" +
                            "- BILLA (0,64)\n" +
                            "- KAUFLAND (0,60€)\n"
                )
            }
        }
    }

    @Composable
    fun ItemPage() {
        Column (modifier = Modifier) {
            TopBar()
            ShowDetailItem(Item(name="Horalky", producer = "Sedita"))
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun ItemPreview() {
        ItemPage()
    }


}