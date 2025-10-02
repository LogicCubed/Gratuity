package com.example.gratuity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gratuity.ui.theme.GratuityTheme

val customFont = FontFamily(
    Font(R.font.bebas_neue_regular)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GratuityTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    GradientBackground(innerPadding)
                }
            }
        }
    }
}

@Composable
fun GradientBackground(innerPadding: PaddingValues) {
    var baseValue by remember { mutableStateOf("") }
    var taxValue by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(15f) }
    var splitCount by remember { mutableStateOf(1f) }

    val base = baseValue.toFloatOrNull() ?: 0f
    val tax = taxValue.toFloatOrNull() ?: 0f
    val tip = (tipPercent.toInt() / 100f)
    val splitCountInt = splitCount.toInt().coerceAtLeast(1)

    val totalAmount = base + (base * tax / 100f) + (base * tip)
    val perPersonAmount = totalAmount / splitCountInt

    val fieldColors = TextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        disabledTextColor = Color.White.copy(alpha = 0.6f),
        errorTextColor = Color.Red,
        cursorColor = Color.White,
        errorCursorColor = Color.Red,
        selectionColors = null,
        focusedContainerColor = Color(0xFF2FB522),
        unfocusedContainerColor = Color(0xFF2FB522),
        disabledContainerColor = Color(0xFF2FB522),
        errorContainerColor = Color(0xFF2FB522),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White,
        disabledLabelColor = Color.White.copy(alpha = 0.6f),
        errorLabelColor = Color.Red,
        focusedPlaceholderColor = Color.White.copy(alpha = 0.5f),
        unfocusedPlaceholderColor = Color.White.copy(alpha = 0.5f),
        disabledPlaceholderColor = Color.White.copy(alpha = 0.3f),
        errorPlaceholderColor = Color.Red.copy(alpha = 0.5f)
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF92EB3F),
                        Color(0xFF329409)
                    )
                )
            ),
        color = Color.Transparent
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFF008C10)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "GRATUITY",
                    color = Color.White,
                    fontFamily = customFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = baseValue,
                    onValueChange = { baseValue = it },
                    label = { Text(
                        "Base",
                                fontFamily = customFont,
                                fontSize = 16.sp
                            )   },
                    colors = fieldColors,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )

                TextField(
                    value = taxValue,
                    onValueChange = { taxValue = it },
                    label = { Text(
                        "Tax",
                                fontFamily = customFont,
                                fontSize = 16.sp
                            ) },
                    colors = fieldColors,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(
                            color = Color(0xFF2FB522),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Tip: ${tipPercent.toInt()}%",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = customFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Slider(
                    value = tipPercent,
                    onValueChange = { tipPercent = it },
                    valueRange = 5f..25f,
                    steps = 20,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color(0xFFFFFFFF),
                        inactiveTrackColor = Color(0xFF2FB522),
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(
                            color = Color(0xFF2FB522),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Split: ${splitCount.toInt()}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = customFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Slider(
                    value = splitCount,
                    onValueChange = { splitCount = it },
                    valueRange = 1f..10f,
                    steps = 10,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color(0xFFFFFFFF),
                        inactiveTrackColor = Color(0xFF2FB522)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(
                            color = Color(0xFF008C10),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Per Person: $" + "%.2f".format(perPersonAmount),
                        color = Color.White,
                        fontFamily = customFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(start = 16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(
                            color = Color(0xFF008C10),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Total: $" + "%.2f".format(totalAmount),
                        color = Color.White,
                        fontFamily = customFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(start = 16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}