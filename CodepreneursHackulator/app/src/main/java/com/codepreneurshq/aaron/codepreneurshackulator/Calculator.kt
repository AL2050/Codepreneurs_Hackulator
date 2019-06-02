package com.codepreneurshq.aaron.codepreneurshackulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Calculator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*----------OUTPUT WINDOWS----------**/
        val result = findViewById<TextView>(R.id.result)
        val mathsProcedure = findViewById<TextView>(R.id.mathsProcedure)

        //array of the ids to the number buttons in the calculator these will be used to assign the corresponding
        //output values when the user clicks them

        /*----------NUMERICAL BUTTONS----------**/
        val zero: Button = findViewById(R.id.zero)
        val one: Button = findViewById(R.id.one)
        val two: Button = findViewById(R.id.two)
        val three: Button = findViewById(R.id.three)
        val four: Button = findViewById(R.id.four)
        val five: Button = findViewById(R.id.five)
        val six: Button = findViewById(R.id.six)
        val seven: Button = findViewById(R.id.seven)
        val eight: Button = findViewById(R.id.eight)
        val nine: Button = findViewById(R.id.nine)

        /*----------OPERATOR BUTTONS----------**/
        val ac: Button = findViewById(R.id.AC)
        val del: Button = findViewById(R.id.DEL)

        val add: Button = findViewById(R.id.add)
        val subtract: Button = findViewById(R.id.subtract)
        val divide: Button = findViewById(R.id.divide)
        val multiply: Button = findViewById(R.id.multiply)

        val equal: Button = findViewById(R.id.equal)


        //a value read from typedValue as a String, converted to an integer and stored here before being added
        var intermediateAddValue = 0

        //a value read from typedValue as a String, converted to an integer and stored here before being subtracted
        var intermediateSubtractValue = 0

        //a value read from typedValue as a String, converted to an integer and stored here before being divided
        var intermediateDivideValue = 0

        //a value read from typedValue as a String, converted to an integer and stored here before being multiplied
        var intermediateMultiplyValue = 0

        //the value of the final result which is then outputted to the 'result' window
        var mathematicalResult = 0

        //storing characters to be outputted to mathsProcedure window this is the string outputted to the
        //mathsProcedure window
        var workingOutput = ""

        //stores value : value is converted to float/int if an operator button is pressed
        //typedValue only ever holds numerical characters
        var typedValue = ""

        //flags for first use of operator buttons [false when not first]
        var firstAddVal = true
        var firstDelVal = true

        //values added together are stored here
        val storedAdditions = ArrayList<Int>()

        //flag triggered 'true' when the equals button is pressed and set back to 'false' when either AC or
        //DEL are pressed
        var equalPressed = false

        /*----------TOASTS----------**/
        val timeOfToast = Toast.LENGTH_SHORT

        val invalidExpr = "Invalid operation"
        val arrayIndexExpr = "No expression to delete"
        val doubleAddExpr = "Invalid operation"
        //        val nothingToDELExpression = "No expression to delete"

        val invalidExprToast = Toast.makeText(applicationContext, invalidExpr, timeOfToast)
        val arrayIndexPointerToast = Toast.makeText(applicationContext, arrayIndexExpr, timeOfToast)
        val doubleAddToast = Toast.makeText(applicationContext, doubleAddExpr, timeOfToast)
//        val nullPointerToast = Toast.makeText(applicationContext, nothingToDELExpression, timeOfToast)

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
            intermediateAddValue = 0
            mathematicalResult = 0

            workingOutput = ""
            mathsProcedure.text = workingOutput

            typedValue = ""

            result.text = ""

            storedAdditions.clear()

            firstAddVal = true

            //resetting equals flag, since we have now cleared the stored data, changing the state of the output to
            //the 'result' window. Likewise, in DEL below, the state of the workingOutput and typedValue changes,
            //so we reset the 'equalPressed' flag
            equalPressed = false

            //reset the DEL flag
            firstDelVal = true
        }

        var revisedResult = 0

        var counter = 0

        var numDigits: Int

        //partialAdd is a flag telling us that the user did not finish their workings (didn't press '='),
        //and has decided to use DEL
        var partialSol = true

        del.setOnClickListener {
            //revise the value in the 'result' window using the 'storedAdditions' ArrayList when the user deletes
            //an added value in the 'mathsProcedure' window
            try {
                //'numDigits' reads the length of the value being deleted, then is decremented. Once it reaches zero,
                //this multi-digit value is removed from 'storedAdditions'
                numDigits = storedAdditions[storedAdditions.count() - 1].toString().length - 1 - counter

                System.out.println("last character of workingOutput is  " + workingOutput.takeLast(1))
                System.out.println("firstDelVal flag   $firstDelVal \n")

                when {
                    workingOutput == "" && (storedAdditions.size == 0) -> {
                        workingOutput = ""
                    }
                    firstDelVal and (workingOutput.takeLast(1) == "+") -> {
                        System.out.println("T1")

                        //remove the last value appended to workingOutput
                        workingOutput = workingOutput.dropLast(1)
                        mathsProcedure.text = workingOutput

                        //disable first delete flag
                        firstDelVal = false

                        equalPressed = false
                    }
//                    (workingOutput.takeLast(1) != "+") and (!equalPressed) -> {
//                        System.out.println("T2")
//
//                        //erasing elements from the value just written to memory <- is this necessary following the press of '='?
//                        typedValue = typedValue.dropLast(1)
//
//                        if (numDigits > 0) {
//                            System.out.println("T2.1")
//
//                            //remove the last value appended to workingOutput, and re-evaluate the resulting output for the result window
//                            workingOutput = workingOutput.dropLast(1)
//                            mathsProcedure.text = workingOutput
//
//                            //keeping track of the number of digits remaining in hte number being deleted.
//                            //once the numDigits variable reaches zero, this value is removed from storedAdditions
//                            counter += 1
//                        }
//                        else {
//                            //reset counter for DEL operations on multi-digit number
//                            counter = 0
//
//                            //disable first delete flag
//                            firstDelVal = false
//
//                            //first number is workingOutput now deleted
//                            partialSol = false
//                        }
//                    }
                    firstDelVal and (workingOutput.takeLast(1) != "+") and (equalPressed) -> {
                        System.out.println("T3")

                        //erasing elements from the value just written to memory <- is this necessary following the press of '='?
                        typedValue = typedValue.dropLast(1)

                        if (numDigits > 0) {
                            System.out.println("T3.1")

                            //remove the last value appended to workingOutput, and re-evaluate the resulting output for the result window
                            workingOutput = workingOutput.dropLast(1)
                            mathsProcedure.text = workingOutput

                            //keeping track of the number of digits remaining in hte number being deleted.
                            //once the numDigits variable reaches zero, this value is removed from storedAdditions
                            counter += 1
                        } else {
                            System.out.println("T3.2")

                            //remove the last value appended to workingOutput, and re-evaluate the resulting output for the result window
                            workingOutput = workingOutput.dropLast(1)
                            mathsProcedure.text = workingOutput

                            storedAdditions.removeAt(storedAdditions.size - 1)

                            //evaluate the revised output to the 'results' window
                            for (element in storedAdditions) {
                                revisedResult += element
                            }
                            //display result
                            result.text = revisedResult.toString()

                            //reset revised result for next revision
                            revisedResult = 0
                            //reset counter for DEL operations on multi-digit number
                            counter = 0
                        }

                        //disable first delete flag
                        firstDelVal = false

                        equalPressed = false


                        System.out.println("T3 revised number of digits in last value in storedAdditions is $numDigits")
                    }
                    !firstDelVal and (workingOutput.takeLast(1) == "+") -> {
                        System.out.println("T4")

                        //remove the last value appended to workingOutput, and re-evaluate the resulting output for the result window
                        workingOutput = workingOutput.dropLast(1)
                        mathsProcedure.text = workingOutput

                        //the for-loop and update to the result window is required when the user submits a '+', but not an '='
                        //and decides they want to erase their workings. <- will not effect output having pressed '='

                        //evaluate the revised output to the 'results' window
                        for (element in storedAdditions) {
                            revisedResult += element
                        }
                        //display result
                        result.text = revisedResult.toString()

                        //reset revised result for next revision
                        revisedResult = 0

                        equalPressed = false
                    }
                    !firstDelVal and (workingOutput.takeLast(1) != "+") -> {
                        System.out.println("T5")

                        System.out.println("number of digits in last value in storedAdditions is $numDigits")

                        //get number of digits of last number in storedAdditions, then remove this value from workingOutput (use a counter/pointer variable)
                        //once complete, remove that value from storedAdditions and update the 'result' window

                        if (numDigits > 0) {
                            System.out.println("T5.1")

                            //remove the last value appended to workingOutput, and re-evaluate the resulting output for the result window
                            workingOutput = workingOutput.dropLast(1)
                            mathsProcedure.text = workingOutput
                            counter += 1
                        } else {
                            System.out.println("T5.2")

                            //remove the last value appended to workingOutput, and re-evaluate the resulting output for the result window
                            workingOutput = workingOutput.dropLast(1)
                            mathsProcedure.text = workingOutput

                            storedAdditions.removeAt(storedAdditions.size - 1)

                            //evaluate the revised output to the 'results' window
                            for (element in storedAdditions) {
                                revisedResult += element
                            }
                            //display result
                            result.text = revisedResult.toString()

                            //reset revised result for next revision
                            revisedResult = 0
                            //reset counter for DEL operations on multi-digit number
                            counter = 0
                        }
                    }
                    !firstDelVal and (workingOutput == "") -> {
                        //evaluate the revised output to the 'results' window
                        for (element in storedAdditions) {
                            revisedResult += element
                        }
                        //display result
                        result.text = revisedResult.toString()
                    }
                }

                System.out.println("current state of workingOutput  $workingOutput")
            }
            catch (e : ArrayIndexOutOfBoundsException) {
                arrayIndexPointerToast.show()
            }
        }


        /*----------MATHEMATICAL OPERATORS----------**/

        add.setOnClickListener {
            when {
                firstAddVal and (typedValue == "") -> {
                    invalidExprToast.show()

                    //maintain firstValue flag as true
                    firstAddVal = true
                }
                firstAddVal and (typedValue != "") -> {
                    //pass on previous intermediateValue variable in mathematicalResult variable
                    mathematicalResult += typedValue.toInt()
                    //display result
                    result.text = mathematicalResult.toString()

                    //clear intermediateValue before storing new value
                    intermediateAddValue += mathematicalResult

                    //append value to be added to storedAdditions
                    storedAdditions.add(typedValue.toInt())

                    //clear string containing previous value
                    typedValue = ""

                    //update window to indicate addition operator has been applied
                    workingOutput += "+"
                    mathsProcedure.text = workingOutput

                    //disable first value flag
                    firstAddVal = false
                }
                !firstAddVal and (workingOutput.takeLast(1) == "+") -> {
                    //under the condition that the last button pressed by the user was the '+' button, here the user
                    //attempt to press '+' again, so we output a Toast to tell them this is an invalid operation
                    doubleAddToast.show()
                }
                else -> {
                    //clear intermediateValue before storing new value
                    intermediateAddValue = 0
                    //store value before '+' as a numerical value
                    intermediateAddValue += typedValue.toInt()     //assume integers for now

                    //pass on previous intermediateValue variable in mathematicalResult variable
                    mathematicalResult += intermediateAddValue
                    //display result
                    result.text = mathematicalResult.toString()

                    //append value to be added to storedAdditions
                    storedAdditions.add(typedValue.toInt())

                    //if the first button pressed is an operator (in this case the addition operator), or an attempt is made to
                    //use two operators together, through an exception to prevent the action from taking place
                    //[use try-catch in if (firstVal), above for first condition]

                    //clear string containing previous value
                    typedValue = ""

                    //update window to indicate addition operator has been applied
                    workingOutput += "+"
                    mathsProcedure.text = workingOutput
                }
            }
            System.out.println("storedAdditions:   $storedAdditions")
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
            if (equalPressed == false and (intermediateAddValue != 0)) {
                //revise output to 'result' window
                mathematicalResult += typedValue.toInt()

                //display result
                result.text = mathematicalResult.toString()

                //append value to be added to storedAdditions
                storedAdditions.add(typedValue.toInt())
                System.out.println("storedAdditions following '='  :   " + storedAdditions)

                equalPressed = true
            }
            else if (equalPressed == false and (intermediateAddValue == 0)) {
                equalPressed = true
            }
        }
    }
}
