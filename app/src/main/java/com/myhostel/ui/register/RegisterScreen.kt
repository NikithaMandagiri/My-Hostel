package com.myhostel.ui.register

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.myhostel.R
import com.myhostel.routing.Screen
import com.myhostel.ui.theme.MyHostelTheme
import com.myhostel.utils.OutlineFormField
import com.myhostel.utils.RoundedButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.myhostel.ui.theme.red
import com.myhostel.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val db = Firebase.firestore
    MyHostelTheme {
        Scaffold {
            Column(
                modifier = Modifier.paint(
                    painterResource(id = R.drawable.ic_hostel),
                    contentScale = ContentScale.FillBounds
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(Modifier.padding(bottom = 60.dp)) {
                        Card(
                            modifier = Modifier.padding(
                                top = 10.dp,
                                start = 10.dp,
                                end = 10.dp
                            ),
                            shape = RoundedCornerShape(25.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(5.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(20.dp)
                            ) {
                                Spacer(modifier = Modifier.height(5.dp))
                                OutlineFormField(
                                    value = name,
                                    onValueChange = { text ->
                                        name = text
                                    },
                                    placeholder = "Enter name",
                                    keyboardType = KeyboardType.Text,
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                OutlineFormField(
                                    value = email,
                                    onValueChange = { text ->
                                        email = text
                                    },
                                    placeholder = "Enter email",
                                    keyboardType = KeyboardType.Email,
                                )

                                Spacer(modifier = Modifier.height(5.dp))
                                OutlineFormField(
                                    value = password,
                                    onValueChange = { text ->
                                        password = text
                                    },
                                    placeholder = "Enter Password",
                                    keyboardType = KeyboardType.Password,
                                    visualTransformation = PasswordVisualTransformation(),
                                )

                                Spacer(modifier = Modifier.height(5.dp))
                                RoundedButton(
                                    text = "Register",
                                    onClick = {
                                        if (name.isNotEmpty()) {
                                            if (email.isNotEmpty()) {
                                                if (password.isNotEmpty()) {
                                                    val user = hashMapOf(
                                                        "name" to name,
                                                        "email" to email,
                                                        "password" to password
                                                    )
                                                    db.collection("users")
                                                        .get()
                                                        .addOnSuccessListener { result ->
                                                            if (result.isEmpty) {
                                                                db.collection("users")
                                                                    .add(user)
                                                                    .addOnSuccessListener { documentReference ->
                                                                        Toast.makeText(
                                                                            context,
                                                                            "Register successfully.",
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()

                                                                    }
                                                                    .addOnFailureListener { e ->
                                                                        Toast.makeText(
                                                                            context,
                                                                            e.message.toString(),
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                    }
                                                            } else {
                                                                for (document in result) {
                                                                    if (document.data["email"] == email &&
                                                                        document.data["password"] == password
                                                                    ) {
                                                                        Toast.makeText(
                                                                            context,
                                                                            "Already exists.",
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                        return@addOnSuccessListener
                                                                    } else {
                                                                        db.collection("users")
                                                                            .add(user)
                                                                            .addOnSuccessListener { documentReference ->
                                                                                Toast.makeText(
                                                                                    context,
                                                                                    "Register successfully.",
                                                                                    Toast.LENGTH_SHORT
                                                                                ).show()
                                                                                navController.navigate(Screen.MainScreen.route) {
                                                                                    popUpTo(Screen.RegisterScreen.route) {
                                                                                        inclusive = true
                                                                                    }
                                                                                }
                                                                            }
                                                                            .addOnFailureListener { e ->
                                                                                Toast.makeText(
                                                                                    context,
                                                                                    e.message.toString(),
                                                                                    Toast.LENGTH_SHORT
                                                                                ).show()
                                                                            }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        .addOnFailureListener { exception ->
                                                            Toast.makeText(
                                                                context,
                                                                exception.message.toString(),
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        }
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Please enter password.",
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
                                                "Please enter name.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(80.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Already have an account?",
                                    textAlign = TextAlign.End,
                                )

                                Text(
                                    " Login", modifier = Modifier.clickable {
                                        navController.navigate(Screen.LoginScreen.route) {
                                            popUpTo(Screen.RegisterScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }, textAlign = TextAlign.End,
                                    style = TextStyle(color = red)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                    }
                }
            }


        }
    }
}