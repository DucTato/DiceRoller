package com.example.diceroller

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        activityBinding.rollButton.setOnClickListener { rollDice() }
        rollDice()
    }

    private fun rollDice() {
        val diceToRoll = Dice(6)
        val diceRollResults = arrayOf( diceToRoll.roll(), diceToRoll.roll(), diceToRoll.roll(), diceToRoll.roll())

        /**
         * This part is for updating the textView component
         **/
        val resultTextView: TextView = findViewById(R.id.redText)

        /**
         * This part is for updating the imageView component
         **/


        fun setDiceFace(face: Int, diceType: Int) {
            val diceFace: Int
            when (diceType){
                /* 0 = Red */
                0 -> {
                    diceFace = when (face)
                    {
                        1 -> R.drawable.dice_1
                        2 -> R.drawable.dice_2
                        3 -> R.drawable.dice_3
                        4 -> R.drawable.dice_4
                        5 -> R.drawable.dice_5
                        6 -> R.drawable.dice_6
                        else -> R.drawable.dice_6
                    }
                    activityBinding.redDiceView.setImageResource(diceFace)
                }
                /* 1 = Blue */
                1 -> {
                    diceFace = when (face)
                    {
                        1 -> R.drawable.bdice_1
                        2 -> R.drawable.bdice_2
                        3 -> R.drawable.bdice_3
                        4 -> R.drawable.bdice_4
                        5 -> R.drawable.bdice_5
                        6 -> R.drawable.bdice_6
                        else -> R.drawable.bdice_6
                    }
                    activityBinding.blueDiceView?.setImageResource(diceFace)
                }
                /* 2 = Purple */
                2 -> {
                    diceFace = when (face)
                    {
                        1 -> R.drawable.pdice_1
                        2 -> R.drawable.pdice_2
                        3 -> R.drawable.pdice_3
                        4 -> R.drawable.pdice_4
                        5 -> R.drawable.pdice_5
                        6 -> R.drawable.pdice_6
                        else -> R.drawable.pdice_6
                    }
                    activityBinding.purpleDiceView?.setImageResource(diceFace)
                }
                /* 3 = Orange */
                3 -> {
                    diceFace = when (face)
                    {
                        1 -> R.drawable.odice_1
                        2 -> R.drawable.odice_2
                        3 -> R.drawable.odice_3
                        4 -> R.drawable.odice_4
                        5 -> R.drawable.odice_5
                        6 -> R.drawable.odice_6
                        else -> R.drawable.odice_6
                    }
                    activityBinding.orangeDiceView?.setImageResource(diceFace)
                }
            }
        }

        fun setResult(type: Int) {
            when (type) {
                0 -> activityBinding.redText.text = diceRollResults[type].toString()
                1 -> activityBinding.blueText?.text = diceRollResults[type].toString()
                2 -> activityBinding.purpleText?.text = diceRollResults[type].toString()
                3 -> activityBinding.orangeText?.text = diceRollResults[type].toString()
            }
        }
        fun rollAnimation(type: Int) {
            activityBinding.rollButton.visibility = View.INVISIBLE
            activityBinding.redText.text = getString(R.string.rolling_message)
            activityBinding.blueText?.text = getString(R.string.rolling_message)
            activityBinding.purpleText?.text = getString(R.string.rolling_message)
            activityBinding.orangeText?.text = getString(R.string.rolling_message)
            val countDown = object : CountDownTimer(3000, 200) {
                override fun onTick(p0: Long) {
                    setDiceFace((1..6).random(), type)
                }
                override fun onFinish() {
                    setDiceFace(diceRollResults[type], type)
                    setResult(type)
                    //resultTextView.text = diceRollResults[type].toString()
                    //resultImageView.contentDescription = diceRollResult.toString()
                    activityBinding.rollButton.visibility = View.VISIBLE
                }
            }
            countDown.start()
        }
        rollAnimation(0)
        rollAnimation(1)
        rollAnimation(2)
        rollAnimation(3)
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }

}