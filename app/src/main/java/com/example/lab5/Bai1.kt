package com.example.lab5

import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController

@Composable
fun Bai1(navController: NavController){
    LoginApp()
}

@Composable
fun LoginScreen(){
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    
    if (showDialog){
        DialogComponent(
            onConfirmation = { showDialog= false },
            dialogTitle = "Notification",
            dialogMessage = dialogMessage)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp, 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp))

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it},
            label = { Text(text = "Username") },
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = passWord,
            onValueChange = { passWord = it},
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        RememberMeSwitch()

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if(userName.isNotBlank() && passWord.isNotBlank()){
                dialogMessage = "Login success!"
            }else{
                dialogMessage = "Please enter username and password"
            }
            showDialog = true

        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login")
        }
    }
}

@Composable
fun LoginApp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = CardDefaults.cardColors(containerColor =
            Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation =
            4.dp)
        ) {
            LoginScreen()
        }
    }
}

@Composable
fun DialogComponent(
    onConfirmation : () -> Unit,
    dialogTitle : String,
    dialogMessage: String,
){
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = dialogTitle,
                    style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = dialogMessage,
                    style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onConfirmation,
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )) {
                    Text(text = "OK")
                }
            }
        }
    }
}

@Composable
fun RememberMeSwitch(){
    var isChecked by remember { mutableStateOf(false) }

    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Switch(
            checked = isChecked,
            onCheckedChange = {isChecked = it})
        Text(
            text = "Rememeber Me? ",
            modifier = Modifier.padding(start = 12.dp))
    }
}


