package eu.vinconafta.porovnajto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class FormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormScreen()
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
    fun createStoreForm(modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center) {

            TextField(value = "",
                onValueChange = {},
                label = { Text("Názov obchodu") },

                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

        }
    }

    @Composable
    fun createItem(modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center) {

            TextField(value = "",
                onValueChange = {},
                label = { Text("Názov obchodu") },

                modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp, 2.dp)


            )

            TextField(value = "",
                onValueChange = {},
                label = { Text("EAN Kód") },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp,2.dp)

            )
            TextField(value = "",
                onValueChange = {},
                label = { Text("Názov Položky") },

                modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp,5.dp)

            )
            TextField(value = "",
                onValueChange = {},
                label = { Text("Cena") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

        }
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
            horizontalAlignment = Alignment.End){
            Button(onClick = {},

            ) {
                Text(text = "Vytvoriť")
            }
        }

    }

    @Composable
    fun FormScreen() {
        Column(modifier = Modifier) {
            TopBar()  // Zobrazenie hornej lišty
//            createStoreForm()  // Formulár
            createItem()

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun FormPreview() {
        FormScreen()
    }
}
