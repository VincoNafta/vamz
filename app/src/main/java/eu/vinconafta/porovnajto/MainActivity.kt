package eu.vinconafta.porovnajto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.vinconafta.porovnajto.mvvms.TopBarViewModel
import eu.vinconafta.porovnajto.ui.TopBarSection
import eu.vinconafta.porovnajto.ui.components.lists.CardGrid
import eu.vinconafta.porovnajto.ui.components.lists.CategoryList
import eu.vinconafta.porovnajto.ui.components.lists.ProductList
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
            if (isMain) {
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
                    CardGrid(cardItems = stores, navController)
                    topBarViewModel.setIsMain(true)
                }
                composable(TopBarSection.CATEGORIES.name) {
                    val categories by topBarViewModel.categories.collectAsState()
                    CategoryList(
                        categoryList = categories,
                        navController = navController,
                        topBarViewModel = topBarViewModel
                    )
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

                composable(
                    route = "category/{cat_id}",
                    arguments = listOf(
                        navArgument("cat_id") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val catId = backStackEntry.arguments?.getInt("cat_id")

                    topBarViewModel.setIsMain(false)
                    LaunchedEffect(Unit) {

                    }

                    if (catId != null) {
                        val items by topBarViewModel.getItemsInCategory(catId)
                            .collectAsState(initial = emptyList())
                        ProductList(items = items, navController = navController)
                    }
                }
                composable(route = "storeCategory/{store_id}",
                    arguments = listOf(
                        navArgument("store_id") { type = NavType.IntType })
                ) { backStackEntry ->
                    val storeId = backStackEntry.arguments?.getInt("store_id")

                    topBarViewModel.setIsMain(false)
                    LaunchedEffect(Unit) {
                    }

                    if (storeId != null) {
                        val categories by topBarViewModel.getCategoriesInStore(storeId)
                            .collectAsState(initial = emptyList())
                            CategoryList(categoryList = categories, navController = navController)
                    }
                }
            }



        }
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewCardGrid() {
    // Preview code placeholder
}
