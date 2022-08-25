package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Numbers
        findViewById<TextView>(R.id.btn_0).setOnClickListener { setTextFields("0") }
        findViewById<TextView>(R.id.btn_1).setOnClickListener { setTextFields("1") }
        findViewById<TextView>(R.id.btn_2).setOnClickListener { setTextFields("2") }
        findViewById<TextView>(R.id.btn_3).setOnClickListener { setTextFields("3") }
        findViewById<TextView>(R.id.btn_4).setOnClickListener { setTextFields("4") }
        findViewById<TextView>(R.id.btn_5).setOnClickListener { setTextFields("5") }
        findViewById<TextView>(R.id.btn_6).setOnClickListener { setTextFields("6") }
        findViewById<TextView>(R.id.btn_7).setOnClickListener { setTextFields("7") }
        findViewById<TextView>(R.id.btn_8).setOnClickListener { setTextFields("8") }
        findViewById<TextView>(R.id.btn_9).setOnClickListener { setTextFields("9") }

        // Symbols
        findViewById<TextView>(R.id.left_bracket_btn).setOnClickListener { setTextFields("(") }
        findViewById<TextView>(R.id.right_bracket_btn).setOnClickListener { setTextFields(")") }
        findViewById<TextView>(R.id.plus_btn).setOnClickListener { setTextFields("+") }
        findViewById<TextView>(R.id.minus_btn).setOnClickListener { setTextFields("-") }
        findViewById<TextView>(R.id.mult_btn).setOnClickListener { setTextFields("*") }
        findViewById<TextView>(R.id.divid_button).setOnClickListener { setTextFields("/") }
        findViewById<TextView>(R.id.dot_btn).setOnClickListener { setTextFields(".") }

        // Actions
        findViewById<TextView>(R.id.ac_btn).setOnClickListener { clearTextFields() }
        findViewById<TextView>(R.id.back_btn).setOnClickListener { backTextFields() }
        findViewById<TextView>(R.id.equal_btn).setOnClickListener { calculateTextFields() }
    }

    private fun setTextFields(str: String) {
        val mathOperation = findViewById<TextView>(R.id.math_operation)
        val resultText = findViewById<TextView>(R.id.result_text)

        if(resultText.text != ""){
            mathOperation.text = resultText.text
            resultText.text = ""
        }

        mathOperation.append(str)
    }

    private fun clearTextFields() {
        val mathOperation = findViewById<TextView>(R.id.math_operation)
        val resultText = findViewById<TextView>(R.id.result_text)

        mathOperation.text = ""
        resultText.text = ""
    }

    private fun backTextFields() {
        val mathOperation = findViewById<TextView>(R.id.math_operation)
        val resultText = findViewById<TextView>(R.id.result_text)
        val str = mathOperation.text.toString()

        if(str.isNotEmpty())
            mathOperation.text = str.substring(0, str.length - 1)

        resultText.text = ""

    }

    private fun calculateTextFields() {
        val mathOperation = findViewById<TextView>(R.id.math_operation)
        val resultText = findViewById<TextView>(R.id.result_text)
        try {
            val ex = ExpressionBuilder(mathOperation.text.toString()).build()
            val result = ex.evaluate()
            val longRes = result.toLong()

            if(result == longRes.toDouble())
                resultText.text = longRes.toString()
            else
                resultText.text = result.toString()

        } catch (e: Exception) {
            Log.d("Error", "Response: ${e.message}")
        }
    }
}