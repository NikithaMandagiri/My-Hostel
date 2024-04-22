package com.myhostel.ui.book

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myhostel.R
import com.myhostel.ui.theme.MyHostelTheme
import com.myhostel.ui.theme.red
import com.myhostel.ui.theme.white
import com.myhostel.utils.HostelBorderFeild
import com.myhostel.utils.RoundedButton
import org.json.JSONArray

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalAnimationApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var hostelName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val amenities = arrayListOf<String>("Water coolers ","Water purifiers",
    "Washing machines","TV room","Reading room","Gym","Wi-Fi internet")
    var isBooked by remember { mutableStateOf(false) }
    val selectedNames = remember {
        mutableStateListOf<String>()

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
                            text = "Booking Screen", color = Color.White,
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
                            Icon(imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = red,
                        titleContentColor = Color.White
                    )
                )

                Column(modifier = Modifier.background(color = white)) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(20.dp)
                    ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        HostelBorderFeild(
                            value = hostelName,
                            onValueChange = { text ->
                                hostelName = text
                            },
                            placeholder = "Enter hostel name",
                            keyboardType = KeyboardType.Text,
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        HostelBorderFeild(
                            value = email,
                            onValueChange = { text ->
                                email = text
                            },
                            placeholder = "Enter email",
                            keyboardType = KeyboardType.Email,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        ExposedDropdownMenuBox(
                            expanded = isExpanded,
                            onExpandedChange = { isExpanded = it }
                        ) {
                            TextField(
                                value = selectedNames.joinToString(", "),
                                onValueChange = {},
                                placeholder = {
                                    Text(text = "Select amenities")
                                },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                modifier = Modifier.fillMaxWidth().padding(start = 20.dp,end = 20.dp)
                            )
                            ExposedDropdownMenu(
                                expanded = isExpanded,
                                onDismissRequest = { isExpanded = false }
                            ) {
                                amenities.forEach { name ->
                                    AnimatedContent(
                                        targetState = selectedNames.contains(name)
                                    ) { isSelected ->
                                        if (isSelected) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(text = name)
                                                },
                                                onClick = {
                                                    selectedNames.remove(name)
                                                },
                                                leadingIcon = {
                                                    Icon(
                                                        imageVector = Icons.Rounded.Check,
                                                        contentDescription = null
                                                    )
                                                }
                                            )
                                        } else {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(text = name)
                                                },
                                                onClick = {
                                                    selectedNames.add(name)
                                                },
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        RoundedButton(
                            text = "Submit",
                            onClick = {
                                if (hostelName.isNotEmpty()) {
                                    if (email.isNotEmpty()) {
                                        if (selectedNames.isNotEmpty()) {
                                            isBooked = true
                                        }else{
                                            Toast.makeText(
                                                context,
                                                "Please select amenities.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please enter email.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter hostel name.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
        if (isBooked) {
            AlertDialog(
                onDismissRequest = {
                    isBooked = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Dear student,\nYou have applied for Hostel name: $hostelName and Amenities ${selectedNames.joinToString(", ")}. ") },
                confirmButton = {
                    RoundedButton(
                        text = "Ok",
                        textColor = white,
                        onClick = {
                            navController.navigateUp()
                            isBooked = false
                        }
                    )
                },
                dismissButton = {}
            )
        }


    }
}