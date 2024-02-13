package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMICalculator()
                }
            }
        }
    }
}
@Composable
fun Heading(title:String){
    Text(
        text =title,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        modifier= Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    )
}

@Composable
fun BMICalculator(bmiViewModel: BMIViewModel=viewModel()) {
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Heading(title= "BMI Calculator")
        OutlinedTextField(
            label = {Text(text= stringResource(R.string.weight))},
            value = bmiViewModel.weightInput,
            onValueChange = { newWeightInput ->
                bmiViewModel.weightInput = newWeightInput
                bmiViewModel.updateInputs(newWeightInput, bmiViewModel.heightInput)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            label = {Text(text= stringResource(R.string.height))},
            value = bmiViewModel.heightInput,
            onValueChange = { newHeightInput ->
                bmiViewModel.heightInput = newHeightInput
                bmiViewModel.updateInputs(bmiViewModel.weightInput, newHeightInput)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        val formattedBMI = String.format("%.2f", bmiViewModel.bmi)
        Text(
            text= "Body Mass Index: $formattedBMI",
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BMICalculatorPreview() {
    BMICalculatorTheme {
        BMICalculator()
    }
}