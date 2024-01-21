package com.example.mapcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar

class PruebaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewContainer()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ViewContainer() {
    Scaffold(
        content = { CustomLayout() }
    )
}

@Composable
fun CustomLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TitleTextComponent(text = "Good ${obtenerMomentoDelDia()}!")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageTextComponent(
                imageResource = R.drawable.ic_launcher_foreground,
                text = "Componente 1"
            )
            ImageTextComponent(
                imageResource = R.drawable.ic_launcher_foreground,
                text = "Componente 2"
            )
            ImageTextComponent(
                imageResource = R.drawable.ic_launcher_foreground,
                text = "Componente 3"
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(4) { index ->
                ImageTextComponent(
                    imageResource = R.drawable.ic_launcher_foreground,
                    text = "Componente ${index + 4}"
                )
            }
        }
    }
}

@Composable
fun TitleTextComponent(text: String) {
    Column {
        Text(
            text = text,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun ImageTextComponent(imageResource: Int, text: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

fun obtenerMomentoDelDia(): String {
    val calendar = Calendar.getInstance()

    return when (calendar.get(Calendar.HOUR_OF_DAY)) {
        in 6..11 -> "Morning"
        in 12..20 -> "Evening"
        else -> "Night"
    }
}