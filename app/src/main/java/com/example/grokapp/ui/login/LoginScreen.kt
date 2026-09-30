package com.example.grokapp.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier ,
    onLoginClicked : () -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldExample(
            text = text,
            onValueChange = { text = it }
        )

        Spacer(modifier = modifier.height(12.dp))

        Button(
            onClick = onLoginClicked
        ) {
            Text("Giriş Yap")
        }
    }
}



@Composable
private fun TextFieldExample(
    text : String,
    onValueChange : (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = { Text("Enter Name") }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        modifier = Modifier,
        onLoginClicked = {}
    )
}