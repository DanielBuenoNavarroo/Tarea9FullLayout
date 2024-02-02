package com.example.mapcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        content = { Body() },
        topBar = {
            TopAppBar(
                title = { Text(text = "Search") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                })
        }
    )
}

@Composable
fun Body() {
    Column {
        Text(text = "HOlA")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Siguiente")
        }
    }
}

