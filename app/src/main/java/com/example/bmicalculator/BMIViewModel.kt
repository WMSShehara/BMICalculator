package com.example.bmicalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BMIViewModel: ViewModel() {
    var weightInput by mutableStateOf("")
    var heightInput by mutableStateOf("")
    var bmi by mutableDoubleStateOf(0.0)

    fun updateInputs(weight: String, height: String) {
        weightInput = weight
        heightInput = height
        updateBMI() // Recalculate BMI when inputs change
    }

    private fun updateBMI() {
        var weight = weightInput.toDoubleOrNull() ?: 0.0
        var height = heightInput.toDoubleOrNull() ?: 0.0
        var heightInMeters = height / 100
        if (height != 0.0 || weight != 0.0) {
            val bmiValue = weight / (heightInMeters * heightInMeters)
            bmi = bmiValue
        } else {
            bmi = 0.0
        }
    }
}