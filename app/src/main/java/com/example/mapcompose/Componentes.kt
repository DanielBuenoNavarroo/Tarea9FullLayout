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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import com.example.mapcompose.navegation.NavigationScreens
import com.example.mapcompose.ui.theme.Circulo


class Componentes {
    companion object {
        @Composable
        fun ParteArriba() {
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult()
            ) { _ ->
                // aqui iria una variable que obtenga la búsqueda pero no da tiempo a implementarlo
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
                                        .makeText(
                                            toastContext,
                                            "No tienes intalado google assistant",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                    e.printStackTrace()
                                }
                            }
                    )
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cloudy_svgrepo_com),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Cloudy 90º")
                        }
                    }
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
        fun TopComponent(imageResource: Int, text: String, onClick: () -> Unit) {
            Column(
                modifier = Modifier
                    .width(120.dp)
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxHeight()
                    .clickable { onClick() },
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
        fun TopDiv(navController: NavController) {
            val context = LocalContext.current.applicationContext
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                item {
                    MarginHorizontal(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_location_on_16,
                        text = "Navigate",
                        onClick = {
                            Toast.makeText(context, "hola", Toast.LENGTH_SHORT).show()
                        }
                    )
                    MarginHorizontal(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_call_16,
                        text = "Make a call",
                        onClick = {
                            Toast.makeText(context, "hola", Toast.LENGTH_SHORT).show()
                        }
                    )
                    MarginHorizontal(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_search_24,
                        text = "Search",
                        onClick = {
                            navController.navigate(route = NavigationScreens.SearchScreen.route)
                        }
                    )
                    MarginHorizontal(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_add_circle_outline_16,
                        text = "Otro",
                        onClick = {
                            Toast.makeText(context, "hola", Toast.LENGTH_SHORT).show()
                        }
                    )
                    MarginHorizontal(margin = 8)
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
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(16.dp)
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
                        modifier = Modifier.padding(top = 8.dp),
                        fontSize = 20.sp
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
                        imageResource = R.drawable.baseline_location_on_16,
                        text = "Places history >"
                    )
                    MarginVertical(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.baseline_call_16,
                        text = "Call history >"
                    )
                    MarginVertical(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.baseline_access_alarms_16,
                        text = "Searchs >"
                    )
                    MarginVertical(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.baseline_add_circle_outline_16,
                        text = "Others"
                    )
                    MarginVertical(margin = 90)
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


