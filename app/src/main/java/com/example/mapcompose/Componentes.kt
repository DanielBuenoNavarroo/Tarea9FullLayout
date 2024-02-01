package com.example.mapcompose

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.mapcompose.ui.theme.Circulo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState


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
        fun TopDiv() {
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
                            Log.i("hola", "clica")
                            if (ActivityCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CALL_PHONE
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                Log.i("hola", "no hay permisos")
                                ActivityCompat.requestPermissions(
                                    context as Activity,
                                    arrayOf(Manifest.permission.CALL_PHONE), 101
                                )
                            } else {
                                val telefono = "tel:" + Uri.encode("646136472")
                                val intent = Intent(Intent.ACTION_CALL, Uri.parse(telefono))
                                Log.i("hola", "pasa aqui")
                                try {
                                    startActivity(context, intent, Bundle.EMPTY)
                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "No se puede llamar a ese numero",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    )
                    MarginHorizontal(margin = 8)
                    TopComponent(
                        imageResource = R.drawable.baseline_access_alarms_16,
                        text = "Add alarm",
                        onClick = {
                            Toast.makeText(context, "hola", Toast.LENGTH_SHORT).show()
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
                        text = "Alarms >"
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

        @Composable
        fun LoginScreen(viewModel: LoginViewModel) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Log(Modifier.align(Alignment.Center), viewModel)
            }
        }

        private @Composable
        fun Log(modifier: Modifier, viewModel: LoginViewModel) {

            // Conectamos nuestro mail con el de LoginViewModel
            val email: String by viewModel.email.observeAsState(initial = "")
            val password: String by viewModel.password.observeAsState(initial = "")
            // Solo se activará el botón cuando email y password cumplan nuestras reglas
            val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)

            val context = LocalContext.current
            var auth: FirebaseAuth = Firebase.auth

            Column(modifier = modifier) {
                HeaderImage(Modifier.align(Alignment.CenterHorizontally))
                MarginVertical(margin = 64)
                EmailField(email, { viewModel.onLoginChanged(it, password) })
                MarginVertical(margin = 16)
                PasswordField(password, { viewModel.onLoginChanged(email, it) })
                MarginVertical(margin = 8)
                ForgotPassword(Modifier.align(Alignment.End))
                MarginVertical(margin = 64)
                LoginButton(loginEnable) {
                    try {
                        auth.signInWithEmailAndPassword(
                            email,
                            password
                        ).addOnCompleteListener {
                            if (it.isSuccessful) {
                                val logueado = Intent(context, PruebaActivity::class.java)
                                context.startActivity(logueado)
                            } else {
                                Toast.makeText(context, "Error en la autentificación", Toast.LENGTH_LONG).show()
                            }
                        }
                    } catch (ex: Exception) {
                        Toast.makeText(context, "Error: ${ex.message}", Toast.LENGTH_LONG).show()
                    }
                }
//                MarginVertical(margin = 16)
//                RegisterButton(loginEnable) {
//                    auth.createUserWithEmailAndPassword(
//                        email,
//                        password
//                    ).addOnCompleteListener {
//                        if (it.isSuccessful) {
//                            //Log.d(TAG, "Creado nuevo usuario")
//                            //El email será nuestra id
//                            // Añadimos datos al usuario creado
//                            val usuarioActual: FirebaseUser? = auth.currentUser
//                            if (usuarioActual != null) {
//                                // insertamos los datos del usuario actual en nuestra Base de Datos
//                                val user = Usuario(email)
//                                usuariosRef.child(usuarioActual.uid).setValue(user)
//                            }
//                            Toast.makeText(
//                                context,
//                                "Usuario creado",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        } else {
//                            Toast.makeText(
//                                context,
//                                "Error en la creación de usuario",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//
//                    }
//                }

            }

        }

//        @Composable
//        fun RegisterButton(registerEnable: Boolean, onRegisterSelected: () -> Unit) {
//            Button(
//                onClick = { onRegisterSelected }, modifier = Modifier
//                    .fillMaxWidth()
//                    .height(48.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF1967D2),
//                    disabledContainerColor = Color(0xFFE5EAF0),
//                    contentColor = Color(0xFFFFFFFF),
//                    disabledContentColor = Color(0xFF000000)
//                ),
//                enabled = registerEnable
//            )
//            {
//                Text(text = "Register")
//            }
//        }

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
                    Toast.makeText(context, "Mala Suerte, esta es una aplicación horrible", Toast.LENGTH_LONG).show()
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

    }
}


