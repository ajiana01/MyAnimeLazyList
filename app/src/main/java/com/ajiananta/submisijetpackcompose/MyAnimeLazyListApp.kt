package com.ajiananta.submisijetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ajiananta.submisijetpackcompose.ui.nav.NavItem
import com.ajiananta.submisijetpackcompose.ui.nav.Screen
import com.ajiananta.submisijetpackcompose.ui.screen.detail.DetailScreen
import com.ajiananta.submisijetpackcompose.ui.screen.favorite.FavoriteScreen
import com.ajiananta.submisijetpackcompose.ui.screen.home.HomeScreen
import com.ajiananta.submisijetpackcompose.ui.screen.profile.ProfileScreen
import com.ajiananta.submisijetpackcompose.ui.theme.SubmisiJetpackComposeTheme


@Composable
fun MyAnimeLazyListApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier,
) {
    val navBackSrackEntry = navController.currentBackStackEntry
    val currentRoute = navBackSrackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {
            innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeMenu.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeMenu.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            composable(Screen.FavoriteScreen.route) {
                FavoriteScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(route = Screen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("id") ?: -1L
                DetailScreen(id = id, navigateBack = {
                    navController.navigateUp()
                },
                    navigateToFavorite = {
                        navController.popBackStack()
                        navController.navigate(Screen.FavoriteScreen.route)
                        {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    })
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavItem(
                title = "Beranda",
                icon = Icons.Default.Home,
                screen = Screen.HomeMenu
            ),
            NavItem(
                title = "Favorite",
                icon = Icons.Default.Favorite,
                screen = Screen.FavoriteScreen
            ),
            NavItem(
                title = "Profile",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )
        navigationItem.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )

        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MyAnimeLazyListPreview() {
    SubmisiJetpackComposeTheme {
        MyAnimeLazyListApp(
            modifier = Modifier
        )
    }
}