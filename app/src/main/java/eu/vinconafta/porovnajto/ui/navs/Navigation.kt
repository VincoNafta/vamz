package eu.vinconafta.porovnajto.ui.navs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import eu.vinconafta.porovnajto.mvvms.TopBarViewModel
import eu.vinconafta.porovnajto.ui.TopBarSection
import eu.vinconafta.porovnajto.ui.components.lists.CategoryList
import eu.vinconafta.porovnajto.ui.components.lists.ProductList
import eu.vinconafta.porovnajto.ui.screens.CreateItemScreen
import eu.vinconafta.porovnajto.ui.screens.ItemScreen
import eu.vinconafta.porovnajto.ui.screens.StoreScreen

/**
 * Súbor definujúci URI cesty a prípadné parametre
 */
@Composable
fun NavDefine(navController: NavHostController, topBarViewModel: TopBarViewModel, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = TopBarSection.STORES.name,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(TopBarSection.STORES.name) {
            val stores by topBarViewModel.stores.collectAsState()
            StoreScreen(cardItems = stores, navController)
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
        composable(TopBarSection.PRODUCTS.name) {
            val items by topBarViewModel.items.collectAsState()
            ProductList(items = items, navController = navController)
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
        composable(
            route = "storeCategory/{store_id}",
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

        composable(
            route = "createitem"
        ) {
            CreateItemScreen(navController= navController)

            topBarViewModel.setIsMain(false)
        }
    }
}