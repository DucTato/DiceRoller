package com.example.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
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
        val diceRollResult = diceToRoll.roll()

        /**
         * This part is for updating the textView component
         **/
        val resultTextView: TextView = findViewById(R.id.redText)

        /**
         * This part is for updating the imageView component
         **/
        val resultImageView: ImageView = findViewById(R.id.redDiceView)

        fun setDiceFace(face: Int) {
            val diceFace = when (face) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> R.drawable.dice_6
            }
            resultImageView.setImageResource(diceFace)
        }

        @SuppressLint("SetTextI18n")
        fun rollAnimation() {
            activityBinding.rollButton.visibility = View.INVISIBLE
            resultTextView.text = getString(R.string.rolling_message)
            val countDown = object : CountDownTimer(3000, 200) {
                override fun onTick(p0: Long) {
                    setDiceFace((1..6).random())
                }
                override fun onFinish() {
                    setDiceFace(diceRollResult)
                    resultTextView.text = diceRollResult.toString()
                    resultImageView.contentDescription = diceRollResult.toString()
                    activityBinding.rollButton.visibility = View.VISIBLE
                }
            }
            countDown.start()
        }
        rollAnimation()
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }

}