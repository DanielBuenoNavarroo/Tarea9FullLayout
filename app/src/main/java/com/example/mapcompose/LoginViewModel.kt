package com.example.mapcompose

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    // Creando livedata. solo se podrá modificar desde la View
    private val _email = MutableLiveData<String>()
    val email: LiveData <String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData <String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData <Boolean> = _loginEnable

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData <Boolean> = _registerEnable

    // Función que comprueba si el email y el password son válidos
    fun onLoginChanged (email: String, password : String){
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    fun onRegisterChanged (email: String, password : String){
        _email.value = email
        _password.value = password
        _registerEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    // Función simple para comprobar que hemos metido un pass de más de 6 caracteres
    private fun isValidPassword(password: String): Boolean = password.length >= 6

    // Comprobación de la validez del email
    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()



}
