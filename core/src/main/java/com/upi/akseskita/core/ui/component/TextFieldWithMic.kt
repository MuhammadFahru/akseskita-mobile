package com.upi.akseskita.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upi.akseskita.core.R

@Composable
fun TextFieldWithMic(
    placeholder: String,
    value: TextFieldValue,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onValueChange: (TextFieldValue) -> Unit,
    onClickAction: () -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder, fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                color = Color.Black.copy(alpha = 0.6f)
            )
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            color = Color.Black
        ),
        trailingIcon = {
            Image(
                painter = painterResource(R.drawable.ic_microphone),
                contentDescription = "Icon Microphone",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onClickAction() }
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldWithMicPreview() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextFieldWithMic(
        value = text,
        placeholder = "John Doe",
        onValueChange = { text = it },
        onClickAction = {}
    )
}