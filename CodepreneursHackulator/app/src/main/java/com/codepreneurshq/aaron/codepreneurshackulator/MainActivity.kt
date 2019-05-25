package com.codepreneurshq.aaron.codepreneurshackulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import java.lang.NumberFormatException
import java.lang.reflect.Array.get
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = findViewById<TextView>(R.id.result)
        val mathsProcedure = findViewById<TextView>(R.id.mathsProcedure)

        //array of the ids to the number buttons in the calculator
        //these will be used to assign the corresponding output values when the user clicks them
        //press button '1' gives an output of 1 to the mathsProcedure window

        val zero : Button = findViewById(R.id.zero)
        val one : Button = findViewById(R.id.one)
        val two : Button = findViewById(R.id.two)
        val three : Button = findViewById(R.id.three)
        val four : Button = findViewById(R.id.four)
        val five : Button = findViewById(R.id.five)
        val six : Button = findViewById(R.id.six)
        val seven : Button = findViewById(R.id.seven)
        val eight : Button = findViewById(R.id.eight)
        val nine : Button = findViewById(R.id.nine)

        val ac : Button = findViewById(R.id.AC)
        val del : Button = findViewById(R.id.DEL)

        val add : Button = findViewById(R.id.add)
        val subtract : Button = findViewById(R.id.subtract)
        val divide : Button = findViewById(R.id.divide)
        val multiply : Button = findViewById(R.id.multiply)

        val equal : Button = findViewById(R.id.equal)

        var intermediateValue : Int = 0     //a value read from typedValue, converted to an integer and stored here before being operated on

        var mathematicalResult : Int = 0

        //storing characters to be outputted to mathsProcedure window
        var workingOutput = ""      //this is the string outputted to the mathsProcedure window

        var typedValue = ""         //stores value : value is converted to float/int if an operator button is pressed

        var firstVal : Boolean = true   //used to indicate to operator buttons that they are operating on the
                                        // first value inputted by the user

        //handling button clicks by user
        /*----------NUMBERS----------**/
        zero.setOnClickListener {
            workingOutput += "0"
            typedValue += "0"
            mathsProcedure.text = workingOutput
        }
        one.setOnClickListener {
            workingOutput += "1"
            typedValue += "1"
            mathsProcedure.text = workingOutput
        }
        two.setOnClickListener {
            workingOutput += "2"
            typedValue += "2"
            mathsProcedure.text = workingOutput
        }
        three.setOnClickListener {
            workingOutput += "3"
            typedValue += "3"
            mathsProcedure.text = workingOutput
        }
        four.setOnClickListener {
            workingOutput += "4"
            typedValue += "4"
            mathsProcedure.text = workingOutput
        }
        five.setOnClickListener {
            workingOutput += "5"
            typedValue += "5"
            mathsProcedure.text = workingOutput
        }
        six.setOnClickListener {
            workingOutput += "6"
            typedValue += "6"
            mathsProcedure.text = workingOutput
        }
        seven.setOnClickListener {
            workingOutput += "7"
            typedValue += "7"
            mathsProcedure.text = workingOutput
        }
        eight.setOnClickListener {
            workingOutput += "8"
            typedValue += "8"
            mathsProcedure.text = workingOutput
        }
        nine.setOnClickListener {
            workingOutput += "9"
            typedValue += "9"
            mathsProcedure.text = workingOutput
        }

        /*----------AC AND DEL----------**/
        ac.setOnClickListener {
            workingOutput = ""
            typedValue = ""
            mathsProcedure.text = workingOutput

            intermediateValue = 0
            mathematicalResult = 0

            result.text = "0"

            firstVal = true
        }
        del.setOnClickListener {
           //remove the last value appended to workingOutput, and re-evaluate the resulting output for the result window
            workingOutput = workingOutput.dropLast(1)
            typedValue = typedValue.dropLast(1)
            mathsProcedure.text = workingOutput
        }

        /*----------MATHEMATICAL OPERATORS----------**/
        val invalidExpression = "Invalid operation"
        val timeOfToast = Toast.LENGTH_SHORT
        val invalidExprToast = Toast.makeText(applicationContext, invalidExpression, timeOfToast)

        add.setOnClickListener {
            when {
                firstVal and (typedValue == "") -> {
                    invalidExprToast.show()
                    typedValue = "0"
                    mathematicalResult += typedValue.toInt()
                    result.text = mathematicalResult.toString()
                    firstVal = false
                }
                firstVal and (typedValue != "") -> {
                    mathematicalResult += typedValue.toInt()
                    result.text = mathematicalResult.toString()
                    firstVal = false

                    intermediateValue += mathematicalResult
                    //clear string containing previous value
                    typedValue = ""

                    //update window to indicate addition operator has been applied
                    workingOutput += " + "
                    mathsProcedure.text = workingOutput
                }
                else -> {
                    //pass on previous intermediateValue variable in mathematicalResult variable
                    mathematicalResult += intermediateValue

                    //display result
                    result.text = mathematicalResult.toString()

                    //if the first button pressed is an operator (in this case the addition operator), or an attempt is made to
                    //use two operators together, through an exception to prevent the action from taking place
                    //[use try-catch in if (firstVal), above for first condition]


                    //clear intermediateValue before storing new value
                    intermediateValue = 0
                    //store value before '+' as a numerical value
                    intermediateValue += typedValue.toInt()     //assume integers for now

                    //clear string containing previous value
                    typedValue = ""

                    //update window to indicate addition operator has been applied
                    workingOutput += " + "
                    mathsProcedure.text = workingOutput
                }
            }
        }
//        subtract.setOnClickListener {
//            //update window to indicate subtraction has occurred
//            workingOutput += " - "
//            mathsProcedure.text = workingOutput
//        }
//        divide.setOnClickListener {
//            //update window to indicate addition has occurred
//            workingOutput += " ÷ "
//            mathsProcedure.text = workingOutput
//        }
//        multiply.setOnClickListener {
//            //update window to indicate subtraction has occurred
//            workingOutput += " × "
//            mathsProcedure.text = workingOutput
//        }

        //mathematical operation is stored in memory ready for execution on pressing the '=' button
        //when the '=' button is pressed, the mathematical operation is executed and outputted to the results window
        equal.setOnClickListener {
            //execution of mathematical expression
            //read typedValueString, and translate in mathematical operation

        }
    }
    //the user presses a button, and its corresponding value is stored in memory, ready for calculations
}
