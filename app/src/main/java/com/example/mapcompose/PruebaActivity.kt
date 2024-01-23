package com.example.mapcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Componentes.TitleTextComponent(text = "Good ${obtenerMomentoDelDia()}!")
        Componentes.TopDiv()
        ParaTi()
        Componentes.BottomDiv()
    }
}

@Composable
fun ParaTi(){
    Column {
        Componentes.MarginHorizontal(margin = 32)
        Text(text = "Just for you")
        Componentes.MarginHorizontal(margin = 8)
    }
}

fun obtenerMomentoDelDia(): String {
    val actualTime = Calendar.getInstance()

    return when (actualTime.get(Calendar.HOUR_OF_DAY)) {
        in 6..12 -> "Morning"
        in 13..20 -> "Evening"
        else -> "Night"
    }
}