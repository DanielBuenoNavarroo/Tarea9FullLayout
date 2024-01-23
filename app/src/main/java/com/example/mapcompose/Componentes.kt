package com.example.mapcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapcompose.ui.theme.Circulo

class Componentes {
    companion object {
        @Composable
        fun TitleTextComponent(text: String) {
            Column {
                Text(
                    text = text,
                    modifier = Modifier.padding(bottom = 16.dp),
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    TopComponent(
                        imageResource = R.drawable.baseline_location_on_16,
                        text = "Navigate"
                    )
                    MarginVertical(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_call_16,
                        text = "Make a call"
                    )
                    MarginVertical(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_access_alarms_16,
                        text = "Add alarm"
                    )
                    MarginVertical(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_add_circle_outline_16,
                        text = "Otro"
                    )
                }
            }
        }

        @Composable
        fun BottomComponent(imageResource: Int, text: String) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
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
                    BottomComponent(
                        imageResource = R.drawable.ic_launcher_foreground,
                        text = "Componente"
                    )
                    MarginHorizontal(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.ic_launcher_foreground,
                        text = "Componente"
                    )
                    MarginHorizontal(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.ic_launcher_foreground,
                        text = "Componente"
                    )
                    MarginHorizontal(margin = 16)
                    BottomComponent(
                        imageResource = R.drawable.ic_launcher_foreground,
                        text = "Componente"
                    )
                }
            }
        }

        @Composable
        fun MarginHorizontal(margin: Int) {
            Spacer(modifier = Modifier.height(margin.dp))
        }

        @Composable
        fun MarginVertical(margin: Int) {
            Spacer(modifier = Modifier.width(margin.dp))
        }
    }
}