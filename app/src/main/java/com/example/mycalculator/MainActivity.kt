package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput:TextView?=null
    var lastNumeric:Boolean=false
    var lastDot:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)

    }
    fun onDigit(view: View)
    {
       tvInput?.append((view as Button).text) //when a digit is pressed the last digit is assumed at last number so a dot can be appended
        lastNumeric=true
        lastDot=false


    }

    fun onClear(view: View)
    {
        tvInput?.text=""
    }
    fun onDecimalPoint(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            tvInput?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View)
    {
        tvInput?.text?.let{  //?checks if its empty
            if(lastNumeric && !isOperatorAdded(it.toString()))
            {
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }

    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue=tvInput?.text.toString()
            var prefix=""

            try{

                 if(tvValue.startsWith("-"))
                 {
                     prefix="-"
                     tvValue=tvValue.substring(1)//get rid of the first value....1 is index 1
                 }
                 if(tvValue.contains("-")){
                     val splitValue=tvValue.split("-")
                     var one=splitValue[0]  //eg 99-1 so 99 is one
                     var two=splitValue[1]   // 1 is two
                     if(prefix.isNotEmpty()){
                         one = prefix +one
                     }
                     tvInput?.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                 }else if(tvValue.contains("+")){
                     val splitValue=tvValue.split("+")
                     var one=splitValue[0]  //eg 99-1 so 99 is one
                     var two=splitValue[1]   // 1 is two
                     if(prefix.isNotEmpty()){
                         one = prefix +one
                     }
                     tvInput?.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                 }else if(tvValue.contains("*")){
                     val splitValue=tvValue.split("*")
                     var one=splitValue[0]  //eg 99-1 so 99 is one
                     var two=splitValue[1]   // 1 is two
                     if(prefix.isNotEmpty()){
                         one = prefix +one
                     }
                     tvInput?.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                 }else{
                     val splitValue=tvValue.split("/")
                     var one=splitValue[0]  //eg 99-1 so 99 is one
                     var two=splitValue[1]   // 1 is two
                     if(prefix.isNotEmpty()){
                         one = prefix +one
                     }
                     tvInput?.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                 }


            }catch (e:ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    private fun removeZeroAfterDot(result:String): String{
        var value=result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)
        return value
    }


    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }


}