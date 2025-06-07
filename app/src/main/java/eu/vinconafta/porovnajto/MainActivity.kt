package eu.vinconafta.porovnajto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.vinconafta.porovnajto.mvvms.TopBarViewModel
import eu.vinconafta.porovnajto.ui.components.menus.MainTopBar
import eu.vinconafta.porovnajto.ui.components.menus.OtherTopAppBar
import eu.vinconafta.porovnajto.ui.navs.NavDefine
import eu.vinconafta.porovnajto.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun MainScreen(
    topBarViewModel: TopBarViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val isMain by topBarViewModel.isMain.collectAsState()
    MyApplicationTheme {
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
                NavDefine(navController = navController, topBarViewModel = topBarViewModel, paddingValues = paddingValues)

            }
        )
    }
}