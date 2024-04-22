package com.myhostel.routing

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object MainScreen: Screen("main_screen")
    object ContactUs: Screen("contact_us_screen")
    object HostelDetail: Screen("hostel_detail")
    object AmenitiesScreen: Screen("amenities_screen")
    object BookScreen: Screen("book_screen")


}