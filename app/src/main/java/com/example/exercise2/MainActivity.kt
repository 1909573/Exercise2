package com.example.exercise2

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {

    val df = DecimalFormat("#.###")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener { calculate(it) }
        findViewById<Button>(R.id.buttonReset).setOnClickListener { resetAll(it) }

    }

    private fun calculate(view: View){

        val alertBox = AlertDialog.Builder(this@MainActivity)

        alertBox.setTitle("Error")
        alertBox.setMessage("Please fill in the empty space")
        alertBox.setIcon(R.mipmap.ic_launcher)

        alertBox.setNegativeButton("CLOSE"){dialog, which ->
            dialog.dismiss()
        }



        if(editTextWeight.text.length==0 || editTextHeight.text.length==0){

            alertBox.show()
        }
        else{

            var weight = editTextWeight.text.toString().toInt()
            var height = editTextHeight.text.toString().toDouble()
            var BMI = weight / ((height/100)*(height/100))
            var status = ""

        if(BMI<18.5){
            status = "underweight"
            imageViewProfile.setImageResource(R.drawable.under)

        }
        else if(BMI>=18.5 && BMI<=24.9){
            status = "normal"
            imageViewProfile.setImageResource(R.drawable.normal)
        }
        else{
            status ="overweight"
            imageViewProfile.setImageResource(R.drawable.over)
        }

        textViewBMI.setText("BMI : " + df.format(BMI) +"," + status )
        }

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun resetAll(view:View){

        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
