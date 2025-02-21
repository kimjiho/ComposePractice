package p.ji.kr.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myfirstproject.screen.Home
import p.ji.kr.practice.screen.AppScreen
import p.ji.kr.practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            ComposePracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }) { innerPadding ->
                    NavHost(navController,
                        startDestination = AppScreen.Home.route,
                        modifier = Modifier.padding(innerPadding)) {

                        composable(AppScreen.Home.route) { Home("Home") }
                        composable(AppScreen.Favorite.route) { Home("Favorite") }
                        composable(AppScreen.MyPage.route) { Home("MyPAge") }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    /*
    * BottomNavigation 및 BottomNavigationItem 구성요소를 사용하려면
    * Android 애플리케이션에 androidx.compose.material 종속 항목을 추가합니다.
    * */

    val items = listOf(AppScreen.Home, AppScreen.Favorite, AppScreen.MyPage)

    BottomNavigation(
        backgroundColor = Color.Gray,
        contentColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = when (screen) {
                            AppScreen.Home -> Icons.Filled.Home
                            AppScreen.Favorite -> Icons.Filled.Favorite
                            AppScreen.MyPage -> Icons.Filled.Person
                        },
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Color.Cyan,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracticeTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}