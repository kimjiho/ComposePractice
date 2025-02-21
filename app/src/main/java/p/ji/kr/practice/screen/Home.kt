package com.example.myfirstproject.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun Home(text: String) {
    Column(modifier = Modifier.padding(all = 2.dp)) {
        Text(
            text ="This is $text",
            fontSize = TextUnit(20f, TextUnitType.Sp)
        )

        Checkbox(false, onCheckedChange = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    Home("12345")
}