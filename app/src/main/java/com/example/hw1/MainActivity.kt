package com.example.hw1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var tries = 0
    private var randomNumber = Random.nextInt(1, 100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun checkGuess(view: View)
    {
        if(guess.text.toString() != "")
        {
            val userGuess = guess.text.toString().toInt()

            if(randomNumber == userGuess)
            {
                hint.text = "You Won! $randomNumber is correct!"
                guess.visibility = View.INVISIBLE
                hideKeyboard(view)
                play_again.visibility = View.VISIBLE
            }
            else if(randomNumber > userGuess)
            {
                hint.text = "Hint: Higher!"
                tries++
            }
            else if(randomNumber < userGuess)
            {
                hint.text = "Hint: Lower!"
                tries++
            }

            num_tries.text = "Number of tries: $tries"
            guess.text.clear()
        }
        else
        {
            Toast.makeText(this, "Enter a number as your guess!", Toast.LENGTH_SHORT).show()
        }
    }

    fun newGame(view: View)
    {
        play_again.visibility = View.INVISIBLE
        guess.text.clear()
        hint.text = "Hint: Begin!"
        tries = 0
        num_tries.text = "Number of tries: $tries"
        randomNumber = Random.nextInt(1, 100)
        guess.visibility = View.VISIBLE
    }

    private fun hideKeyboard(view: View){
        view?.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
