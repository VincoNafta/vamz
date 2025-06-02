package eu.vinconafta.porovnajto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.vinconafta.porovnajto.datas.StoreItem
import eu.vinconafta.porovnajto.mvvms.TopBarViewModel
import eu.vinconafta.porovnajto.ui.TopBarSection
import eu.vinconafta.porovnajto.ui.components.CategoryList
import eu.vinconafta.porovnajto.ui.components.ProductList
import eu.vinconafta.porovnajto.ui.components.menus.MainTopBar
import eu.vinconafta.porovnajto.ui.components.menus.OtherTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(
    topBarViewModel: TopBarViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isMain by topBarViewModel.isMain.collectAsState()
    Scaffold(

        topBar = {
            if (isMain == true) {
                MainTopBar(viewModel = topBarViewModel, navController = navController)
            } else {
                OtherTopAppBar {
                    navController.popBackStack()
                }
            }
        },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = TopBarSection.STORES.name,
                modifier = Modifier.padding(paddingValues)
            ) {

                composable(TopBarSection.STORES.name) {
                    val stores by topBarViewModel.stores.collectAsState()
                    CardGrid(cardItems = stores)
                    topBarViewModel.setIsMain(true)
                }
                composable(TopBarSection.CATEGORIES.name) {
                    val categories by topBarViewModel.categories.collectAsState()
                    CategoryList(categoryList = categories, topBarViewModel = topBarViewModel)
                    topBarViewModel.setIsMain(true)
                }
                composable(TopBarSection.SETS.name) {
                    val items by topBarViewModel.items.collectAsState()
                    ProductList(items = items, navController = navController)
                    topBarViewModel.setIsMain(true)
                }
                composable(TopBarSection.PRODUCTS.name) {
                    Text("Tu budú produkty", modifier = Modifier.padding(16.dp))
                    topBarViewModel.setIsMain(true)
                }

                composable(
                    route = "item/{id}",
                    arguments = listOf(
                        navArgument("id") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val itemId = backStackEntry.arguments?.getInt("id")

                    if (itemId != null) {
                        ItemScreen(itemId = itemId)
                    }

                    topBarViewModel.setIsMain(false)
                }
            }
        }
    )
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
    // Preview code placeholder
}
