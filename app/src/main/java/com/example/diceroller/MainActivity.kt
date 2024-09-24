package com.example.diceroller

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val diceImages = arrayOf(
        R.drawable.d1,
        R.drawable.d2,
        R.drawable.d3,
        R.drawable.d4,
        R.drawable.d5,
        R.drawable.d6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Edge-to-edge layout insets handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rollButton: Button = findViewById(R.id.button3)
        val diceImageView: ImageView = findViewById(R.id.imageView)

        rollButton.setOnClickListener {
            rollDice(diceImageView)
        }
    }

    private fun rollDice(imageView: ImageView) {
        val randomRoll = (1..6).random()
        val drawableResource = diceImages[randomRoll - 1]

        // Apply dice roll animation
        val shakeAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.shake)
        imageView.startAnimation(shakeAnimation)

        // Set the dice image after animation ends
        shakeAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // Optional: Add sound or pre-animation effects
            }

            override fun onAnimationEnd(animation: Animation) {
                imageView.setImageResource(drawableResource)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }
}
