package com.myhostel.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.myhostel.ui.theme.MyHostelTheme
import com.myhostel.ui.theme.red
import com.myhostel.ui.theme.white
import com.myhostel.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
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
                            text = "Hostel Management", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = red,
                        titleContentColor = Color.White
                    )
                )
                Column(modifier = Modifier.background(color = white)) {
                    Spacer(Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_hostel),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    Text(
                        "Narayan Hostel",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Text(
                        "Price : 12000.00",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Text(
                        "Address : Malviya Nagar",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Text(
                        "Amenities :-",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Text(
                        "A hostel is a lower-priced inn of sorts that offers basic, shared accommodations. Typically, a hostel features a large room with separate beds, a shared bathroom, and a communal kitchen. Some hostels have private rooms, but the lower-cost ones generally offer bunk beds.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Box(Modifier.padding(15.dp)) {
                        RoundedButton(
                            text = "Contact Us",
                            onClick = {
                                navController.navigate(Screen.ContactUs.route)
                            }
                        )
                    }
                }
            }
        }


    }

}