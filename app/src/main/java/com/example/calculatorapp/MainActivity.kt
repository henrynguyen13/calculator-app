package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input.text = "0"

        button_clear_entry.setOnClickListener {
            input.text = handleClearEntry(inputValue = input.text.toString())
        }

        button_clear.setOnClickListener {
            input.text = "0"
            output.text = ""
        }
        button_backspace.setOnClickListener {
            input.text = removeLast(input.text.toString())
        }
        button_0.setOnClickListener {
            input.text = addToInputText("0")
        }
        button_1.setOnClickListener {
            input.text = addToInputText("1")
        }
        button_2.setOnClickListener {
            input.text = addToInputText("2")
        }
        button_3.setOnClickListener {
            input.text = addToInputText("3")
        }
        button_4.setOnClickListener {
            input.text = addToInputText("4")
        }
        button_5.setOnClickListener {
            input.text = addToInputText("5")
        }
        button_6.setOnClickListener {
            input.text = addToInputText("6")
        }
        button_7.setOnClickListener {
            input.text = addToInputText("7")
        }
        button_8.setOnClickListener {
            input.text = addToInputText("8")
        }
        button_9.setOnClickListener {
            input.text = addToInputText("9")
        }
        button_division.setOnClickListener {
            input.text = addToInputText("÷") // ALT + 0247
        }
        button_multiply.setOnClickListener {
            input.text = addToInputText("×") // ALT + 0215
        }
        button_subtraction.setOnClickListener {
            input.text = addToInputText("-")
        }
        button_addition.setOnClickListener {
            input.text = addToInputText("+")
        }

        button_equals.setOnClickListener {
            showResult()
        }
    }

    private fun removeLast(inputValue: String): String{
        if(inputValue.length <=1) return "0"
        return inputValue.substring(0, inputValue.length - 1)
    }
    private fun handleClearEntry(inputValue: String): String{
        val operators = "+-×÷"
        val lastOperatorIndex = inputValue.lastIndexOfAny(operators.toCharArray())

        return if (lastOperatorIndex != -1) {
            inputValue.substring(0,lastOperatorIndex + 1)
        } else {
            inputValue
        }
    }

    private fun addToInputText(buttonValue: String): String {
        if( input.text.toString().last() == '+' ||
            input.text.toString().last() == '-' ||
            input.text.toString().last() == '×' ||
            input.text.toString().last() == '÷' ){
            if(buttonValue == "+" || buttonValue == "-" || buttonValue == "×" || buttonValue == "÷"){
                return input.text.toString()
            }
        }
        if(input.text.toString() == "0") input.text = ""
        return "${input.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                output.text = "Error"
                output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                output.text = DecimalFormat("0.######").format(result).toString()
            }
        } catch (e: Exception) {
            // Show Error Message
            output.text = "Error"
            output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}