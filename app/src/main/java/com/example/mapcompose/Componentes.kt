package com.example.mapcompose

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapcompose.ui.theme.Circulo

class Componentes {
    companion object {

        @Composable
        fun ParteArriba() {
            // Crear el launcher para el resultado de la actividad
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult()
            ) { result ->

            }

            val toastContext = LocalContext.current.applicationContext

            Column(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_assistant_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                try {

                                    val intent = Intent(Intent.ACTION_ASSIST)
                                    launcher.launch(intent)
                                } catch (e: ActivityNotFoundException) {
                                    Toast
                                        .makeText(toastContext, "No tienes intalado google assistant", Toast.LENGTH_SHORT)
                                        .show()
                                    e.printStackTrace()
                                }
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.google_assistant_logo),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }

        @Composable
        fun TitleTextComponent(text: String) {
            Column {
                Text(
                    text = text,
                    fontSize = 32.sp
                )
            }
        }

        @Composable
        fun TopComponent(imageResource: Int, text: String) {
            Column(
                modifier = Modifier
                    .width(125.dp)
                    .clip(MaterialTheme.shapes.large)
                    .shadow(2.dp)
                    .padding(16.dp)
                    .background(Color.White)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(color = Circulo)
                        .padding(8.dp)
                )

                Text(
                    text = text,
                    color = Circulo,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        @Composable
        fun TopDiv() {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                item {
                    TopComponent(
                        imageResource = R.drawable.baseline_location_on_16,
                        text = "Navigate"
                    )
                    MarginHorizontal(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_call_16,
                        text = "Make a call"
                    )
                    MarginHorizontal(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_access_alarms_16,
                        text = "Add alarm"
                    )
                    MarginHorizontal(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_add_circle_outline_16,
                        text = "Otro"
                    )
                }
            }
        }

        @Composable
        fun ParaTi() {
            Column {
                MarginVertical(margin = 32)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Circulo)
                            .padding(8.dp)
                    )
                    MarginHorizontal(margin = 8)
                    Text(text = "Just for you")
                }
                MarginVertical(margin = 8)
            }
        }

        @Composable
        fun BottomComponent(imageResource: Int, text: String) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .clip(MaterialTheme.shapes.large)
                    .shadow(1.dp)
                    .padding(16.dp)
                    .background(Color.White),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Circulo)
                            .padding(8.dp)
                    )

                    Text(
                        text = text,
                        color = Circulo,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        @Composable
        fun BottomDiv() {
            LazyColumn {
                item {
                    MarginVertical(margin = 8)
                    BottomComponent(
                        imageResource = R.drawable.ic_launcher_foreground,
                        text = "Componente"
                    )
                    MarginVertical(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.ic_launcher_foreground,
                        text = "Componente"
                    )
                    MarginVertical(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.ic_launcher_foreground,
                        text = "Componente"
                    )
                    MarginVertical(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.ic_launcher_foreground,
                        text = "Componente"
                    )
                }
            }
        }

        @Composable
        fun MarginVertical(margin: Int) {
            Spacer(modifier = Modifier.height(margin.dp))
        }

        @Composable
        fun MarginHorizontal(margin: Int) {
            Spacer(modifier = Modifier.width(margin.dp))
        }
    }
}