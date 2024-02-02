package com.example.mapcompose.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mapcompose.Componentes
import com.example.mapcompose.LoginViewModel
import com.example.mapcompose.R
import com.example.mapcompose.navegation.NavigationScreens
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {
    Scaffold {
        LoginContent(loginViewModel, navController)
    }
}

@Composable
fun LoginContent(viewModel: LoginViewModel, navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Log(Modifier.align(Alignment.Center), viewModel, navController)
    }
}

@Composable
fun Log(modifier: Modifier, viewModel: LoginViewModel, navController: NavController) {

    // Conectamos nuestro mail con el de LoginViewModel
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    // Solo se activará el botón cuando email y password cumplan nuestras reglas
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)

    val context = LocalContext.current
    var auth: FirebaseAuth = Firebase.auth

    Column(modifier = modifier) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Componentes.MarginVertical(margin = 64)
        EmailField(email) { viewModel.onLoginChanged(it, password) }
        Componentes.MarginVertical(margin = 16)
        PasswordField(password) { viewModel.onLoginChanged(email, it) }
        Componentes.MarginVertical(margin = 8)
        ForgotPassword(Modifier.align(Alignment.End))
        Componentes.MarginVertical(margin = 64)
        LoginButton(loginEnable) {
            try {
                auth.signInWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        navController.navigate(route = NavigationScreens.AppScreen.route)
                    } else {
                        Toast.makeText(context, "Error en la autentificación", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } catch (ex: Exception) {
                Toast.makeText(context, "Error: ${ex.message}", Toast.LENGTH_LONG).show()
            }
        }

    }

}

// Pasar Parámetros SSOT (Single Source Of Truth)
@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() }, modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1B3A57),
            disabledContainerColor = Color(0xFFE5EAF0),
            contentColor = Color(0xFFFFFFFF),
            disabledContentColor = Color(0xFF000000)
        ),
        enabled = loginEnable
    )
    {
        Text(text = "Login")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    val context = LocalContext.current
    Text(
        text = "Olvidaste la contraseña?",
        modifier = Modifier.clickable {
            Toast.makeText(
                context,
                "Mala Suerte, esta es una aplicación horrible",
                Toast.LENGTH_LONG
            ).show()
        },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF018786)
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    var passwordVisibility = remember {
        mutableStateOf(false)
    }
    TextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE5EAF0)),
        //Hint
        placeholder = {
            Text(
                text = "Password",
                color = Color(0xFFBB86FC)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(color = Color(0xFF1B3A57)),
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                Icon(
                    imageVector = if (passwordVisibility.value) {
                        Icons.Default.Lock
                    } else {
                        Icons.Default.Lock
                    },
                    contentDescription = ""
                )
            }
        },
        visualTransformation = if (passwordVisibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE5EAF0)),
        placeholder = {
            Text(
                text = "Email",
                color = Color(0xFFBB86FC)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(color = Color(0xFF1B3A57))
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.firebase),
        contentDescription = "Firebase"
    )
}