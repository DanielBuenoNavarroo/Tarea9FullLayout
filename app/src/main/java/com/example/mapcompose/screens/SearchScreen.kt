package com.example.mapcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(){
    Scaffold {
        Body()
    }
}

@Composable
fun Body(){
    Column {
        Text(text = "HOlA")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Siguiente")
        }
    }
}

