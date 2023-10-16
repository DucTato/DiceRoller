package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
//            val rollMessage: Toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
//            rollMessage.show()
//            val resultTextView: TextView = findViewById(R.id.textView)
//            resultTextView.text = "6"
            rollDice()
        }
        rollDice()
    }

    private fun rollDice() {
        val diceToRoll = Dice(6)
        val diceRollResult = diceToRoll.roll()

        /**
         * This part is for updating the textView component
         **/
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRollResult.toString()
        /**
         * This part is for updating the imageView component
         **/
        val resultImageView: ImageView = findViewById(R.id.imageView)
//        when (diceRollResult) {
//            1 -> resultImageView.setImageResource(R.drawable.dice_1)
//            2 -> resultImageView.setImageResource(R.drawable.dice_2)
//            3 -> resultImageView.setImageResource(R.drawable.dice_3)
//            4 -> resultImageView.setImageResource(R.drawable.dice_4)
//            5 -> resultImageView.setImageResource(R.drawable.dice_5)
//            6 -> resultImageView.setImageResource(R.drawable.dice_6)
//        }
        val diceFace = when (diceRollResult) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_6
        }
        resultImageView.setImageResource(diceFace)
        resultImageView.contentDescription = diceRollResult.toString()
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }

}