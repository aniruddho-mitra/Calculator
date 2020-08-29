package com.example.android.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity()
{
    var lastNumeric:Boolean=false
    var lastDot:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view:View) {
        tvInput.append((view as Button).text)
        lastNumeric=true
    }

    fun onClear(view: View)
    {
        tvInput.text=""
        lastDot=false
        lastNumeric=false
    }

    fun onDecimalPoint(view: View)
    {
        if(lastNumeric&&!lastDot)
        {
            tvInput.append(".")
        }
        lastNumeric=false
        lastDot=true
    }

    fun isOperatorAdded(value:String):Boolean
    {
        return if (value.startsWith("-"))
        {
            false
        }
        else
        {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    fun onOperator(view: View)//to add an operator
    {
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString()))
        {
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }

    fun onEqual(view: View)
    {
        if (lastNumeric)
        {
            var tvValue: String = tvInput.text.toString()
            var prefix=""
            try
            {
                if(tvValue.startsWith("-"))
                {
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if (tvValue.contains("-"))
                {
                    var splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvInput.text=(one.toDouble()-two.toDouble()) .toString()
                }
                else if (tvValue.contains("+"))
                {
                    var splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvInput.text=(one.toDouble()+two.toDouble()) .toString()
                }
                else if (tvValue.contains("/"))
                {
                    var splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvInput.text=(one.toDouble()/two.toDouble()) .toString()
                }
                else if (tvValue.contains("*"))
                {
                    var splitValue=tvValue.split("*")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvInput.text=(one.toDouble()*two.toDouble()) .toString()
                }
            }
            catch (e: ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }
}