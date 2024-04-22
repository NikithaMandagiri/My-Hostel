package com.myhostel.utils.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.myhostel.ui.theme.white
import com.myhostel.R
import com.myhostel.ui.theme.red

@Composable
fun DrawerHeader() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = red).padding(top = 45.dp)
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = R.drawable.ic_hostel),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape).size(150.dp)
        )
        Spacer(modifier = Modifier)

        androidx.compose.material3.Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = white,
        )
    }
}

@Composable
fun DrawerBody(closeNavDrawer: () -> Unit,closeAmenities: () -> Unit, onLogout: () -> Unit) {
    Column {
        DrawerMenuItem(
            text = "Contact Us",
            onItemClick = {
                closeNavDrawer()
            }
        )
        DrawerMenuItem(
            text = "Amenities",
            onItemClick = {
                closeAmenities()
            }
        )
        DrawerMenuItem(
            text = "Logout",
            onItemClick = {
                onLogout()
            }
        )
    }
}

@Composable
private fun DrawerMenuItem(
    text: String,
    onItemClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {onItemClick()}
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = white )
    }
}
