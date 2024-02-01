package com.example.mapcompose

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        // aqui iria una variable que obtenga la búsqueda pero no da tiempo a implementarlo
    }

    val toastContext = LocalContext.current.applicationContext

    Scaffold(
        content = { CustomLayout() },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Componentes.MarginHorizontal(margin = 16)
                    Image(
                        painter = painterResource(id = R.drawable.google_mic_seeklogo),
                        contentDescription = null,
                        modifier = Modifier.clickable {
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
                },
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .shadow(40.dp),
                tonalElevation = 1.dp,
                containerColor = Color.White,
                contentPadding = PaddingValues(16.dp)
            )
        }
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
        Componentes.ParteArriba()
        Componentes.TitleTextComponent(text = "Good ${obtenerMomentoDelDia()}!")
        Componentes.MarginVertical(margin = 32)
        Componentes.TopDiv()
        Componentes.ParaTi()
        Componentes.BottomDiv()
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