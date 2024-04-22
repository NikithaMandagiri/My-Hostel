package com.myhostel.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myhostel.ui.amenities.AmenitiesScreen
import com.myhostel.ui.book.BookScreen
import com.myhostel.ui.contact_us.ContactUsScreen
import com.myhostel.ui.hostel_detail.HostelDetailScreen
import com.myhostel.ui.login.LoginScreen
import com.myhostel.ui.main.MainScreen
import com.myhostel.ui.register.RegisterScreen
import com.myhostel.ui.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.ContactUs.route) {
            ContactUsScreen(navController = navController)
        }
        composable(route = Screen.HostelDetail.route) {
            HostelDetailScreen(navController = navController)
        }
        composable(route = Screen.AmenitiesScreen.route) {
            AmenitiesScreen(navController = navController)
        }
        composable(route = Screen.BookScreen.route) {
            BookScreen(navController = navController)
        }
    }

}