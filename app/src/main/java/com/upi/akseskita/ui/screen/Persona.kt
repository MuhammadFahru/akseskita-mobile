package com.upi.akseskita.ui.screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.upi.akseskita.R
import com.upi.akseskita.ui.component.ButtonFill
import com.upi.akseskita.ui.component.TextFieldWithMic
import com.upi.akseskita.ui.navigation.Screen

@Composable
fun Persona(navController: NavController) {
    var page by remember { mutableIntStateOf(0) }
    val title = if (page == 0 || page == 1) "Kenali Diriku" else "Terima Kasih!"
    val backgroundColor = listOf(
        Color.White,
        colorResource(id = R.color.primary),
        colorResource(id = R.color.tertiary)
    )

    Box(
        modifier = Modifier
            .background(backgroundColor[page])
            .padding(22.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.height(25.dp))

            when (page) {
                0 -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        var isButtonEnabled by remember { mutableStateOf(false) }
                        DisabilityPersona(buttonEnabled = { isButtonEnabled = it })

                        ButtonFill(
                            text = "Selanjutnya",
                            modifier = Modifier.align(Alignment.BottomCenter),
                            enabled = isButtonEnabled
                        ) {
                            page++
                        }
                    }
                }

                1 -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        var isButtonEnabled by remember { mutableStateOf(false) }
                        UserDataPersona(buttonEnabled = { isButtonEnabled = it })

                        ButtonFill(
                            text = "Selesai",
                            modifier = Modifier.align(Alignment.BottomCenter),
                            enabled = isButtonEnabled
                        ) {
                            page++
                        }
                    }
                }

                2 -> {
                    Box {
                        EndPersona()

                        ButtonFill(
                            text = "Ke HomePage",
                            modifier = Modifier.align(Alignment.BottomCenter)
                        ) {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisabilityPersona(
    buttonEnabled: (Boolean) -> Unit
) {
    Column {
        QuestionTitle("1. Apa jenis disabilitas mu?")
        Spacer(modifier = Modifier.height(22.dp))

        val items = listOf(
            R.drawable.ic_tuna_rungu,
            R.drawable.ic_tuna_daksa,
            R.drawable.ic_tuna_netra,
            R.drawable.ic_tuna_wicara
        )
        val itemsSelected = listOf(
            R.drawable.ic_tuna_rungu_selected,
            R.drawable.ic_tuna_daksa_selected,
            R.drawable.ic_tuna_netra_selected,
            R.drawable.ic_tuna_wicara_selected
        )
        val descriptions = listOf("Tuna Rungu", "Tuna Daksa", "Tuna Netra", "Tuna Wicara")
        var selectedIndex by remember { mutableIntStateOf(-1) }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(items) { index, item ->
                val isSelected = selectedIndex == index
                Card(
                    shape = RoundedCornerShape(6.dp),
                    onClick = {
                        selectedIndex = index
                        buttonEnabled(true)
                    },
                    elevation = 0.dp,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(154.dp, 127.dp)
                ) {
                    Image(
                        painter = painterResource(if (isSelected) itemsSelected[index] else item),
                        contentDescription = descriptions[index],
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun UserDataPersona(
    buttonEnabled: (Boolean) -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var selectedGender by remember { mutableStateOf("") }

    val genders = listOf("Pria" to "Pria", "Wanita" to "Wanita")
    val checkboxes = listOf(
        "Fasilitas Umum" to false,
        "Dukungan Kesehatan" to false,
        "Pendidikan" to false,
        "Rekomendasi Tempat" to false
    ).map { it.first to remember { mutableStateOf(it.second) } }

    if (name.text != "" && age.text != "" && selectedGender != "" && checkboxes.any { it.second.value }) {
        buttonEnabled(true)
    }

    Column {
        QuestionTitle("2. Siapa nama mu?")
        Spacer(modifier = Modifier.height(22.dp))
        TextFieldWithMic(
            placeholder = "John Doe",
            value = name,
            onValueChange = { name = it },
            onClickAction = {}
        )
        Spacer(modifier = Modifier.height(22.dp))

        QuestionTitle("3. Berapa usia mu?")
        Spacer(modifier = Modifier.height(22.dp))
        TextFieldWithMic(
            placeholder = "20",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = age,
            onValueChange = { age = it },
            onClickAction = {}
        )
        Spacer(modifier = Modifier.height(22.dp))

        QuestionTitle("4. Apa jenis kelamin mu?")
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            genders.forEach { (label, value) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = selectedGender == value,
                        onClick = { selectedGender = value },
                        colors = RadioButtonDefaults.colors(Color.Black)
                    )
                    Text(
                        text = label,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(22.dp))
                }
            }
        }

        QuestionTitle("5. Layanan yang paling dibutuhkan?")
        Spacer(modifier = Modifier.height(12.dp))
        Column {
            checkboxes.forEach { (label, state) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(32.dp)
                ) {
                    Checkbox(
                        checked = state.value,
                        onCheckedChange = { state.value = it },
                        colors = CheckboxDefaults.colors(Color.Black)
                    )
                    Text(
                        text = label,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun EndPersona() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.height(62.dp))
            Image(
                painter = painterResource(R.drawable.ic_thumbs_up),
                contentDescription = "Icon Thumbs Up",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(206.dp),
            )
            Spacer(modifier = Modifier.height(106.dp))
            Image(
                painter = painterResource(R.drawable.ic_quote),
                contentDescription = "Icon Quotes",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(21.dp),
            )
            Text(
                text = "Informasi yang Anda berikan sangat berharga bagi kami untuk meningkatkan pengalaman Anda dalam menggunakan aplikasi ini.",
                fontSize = 24.sp,
                lineHeight = 26.sp,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            )
        }
    }
}

@Composable
fun QuestionTitle(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
    )
}