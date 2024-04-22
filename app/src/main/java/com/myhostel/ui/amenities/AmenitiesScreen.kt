package com.myhostel.ui.amenities

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myhostel.R
import com.myhostel.routing.Screen
import com.myhostel.ui.model.HostelModel
import com.myhostel.ui.theme.MyHostelTheme
import com.myhostel.ui.theme.red
import com.myhostel.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AmenitiesScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val list = arrayListOf<HostelModel>().apply {
        add(HostelModel(id = "", image = "", name = "Water coolers"))
        add(HostelModel(id = "", image = "", name = "Water purifiers"))
        add(HostelModel(id = "", image = "", name = "Washing machines"))
        add(HostelModel(id = "", image = "", name = "TV room"))
        add(HostelModel(id = "", image = "", name = "Reading room"))
        add(HostelModel(id = "", image = "", name = "Gym"))
        add(HostelModel(id = "", image = "", name = "Wi-Fi internet"))
    }
    MyHostelTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = red)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Amenities", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = red,
                        titleContentColor = Color.White
                    )
                )
                Column(modifier = Modifier.background(color = white)) {
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
        }


    }

}