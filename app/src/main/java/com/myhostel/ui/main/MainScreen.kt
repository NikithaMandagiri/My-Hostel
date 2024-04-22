package com.myhostel.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myhostel.R
import com.myhostel.routing.Screen
import com.myhostel.ui.hostelDatabase.HostelDatabase
import com.myhostel.ui.model.HostelModel
import com.myhostel.ui.theme.MyHostelTheme
import com.myhostel.ui.theme.red
import com.myhostel.ui.theme.white
import com.myhostel.utils.RoundedButton
import com.myhostel.utils.drawer.DrawerBody
import com.myhostel.utils.drawer.DrawerHeader
import com.myhostel.utils.drawer.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val database = remember {
        HostelDatabase(context)
    }
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }
    val list = arrayListOf<HostelModel>().apply {
        add(HostelModel(id = "", image = "", name = "Cornell Quarter"))
        add(HostelModel(id = "", image = "", name = "King Edward's Square"))
        add(HostelModel(id = "", image = "", name = "Parkside Halls"))
        add(HostelModel(id = "", image = "", name = "West Parkside Village"))
        add(HostelModel(id = "", image = "", name = "East Parkside Village"))
        add(HostelModel(id = "", image = "", name = "Woodlands"))
    }
    MyHostelTheme {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = red),
            drawerContent = {
                DrawerHeader()
                DrawerBody(closeNavDrawer = {
                    navController.navigate(Screen.ContactUs.route)
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }, closeAmenities = {
                    navController.navigate(Screen.AmenitiesScreen.route)
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }, onLogout = {
                    isLogout = true
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            },
            backgroundColor = red,
            contentColor = red,
            drawerBackgroundColor = red
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            Column(
                modifier = Modifier
                    .background(color = red)
                    .verticalScroll(scrollState)
            ) {
                Spacer(Modifier.height(10.dp))
                list.forEachIndexed { index, productModel ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable {
                                navController.navigate(Screen.HostelDetail.route)
                            },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_hostel),
                            contentDescription = "Image",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        )
                        Text(
                            productModel.name ?: "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(color = Color.Black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }
            }
        }
        if (isLogout) {
            AlertDialog(
                onDismissRequest = {
                    isLogout = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Are you sure you want to logout ?") },
                confirmButton = {
                    RoundedButton(
                        text = "Cancel",
                        textColor = white,
                        onClick = { isLogout = false }
                    )
                },
                dismissButton = {

                    RoundedButton(
                        text = "Logout",
                        textColor = white,
                        onClick = {
                            database.saveData("isLogin", false)
                            navController.navigate(
                                Screen.LoginScreen.route
                            ) {
                                popUpTo(Screen.MainScreen.route) {
                                    inclusive = true
                                }
                            }
                            isLogout = false
                        }
                    )

                }
            )
        }


    }

}