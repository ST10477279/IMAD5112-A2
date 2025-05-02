package com.example.flashcardsapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcardsapp.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val reviewAnswers = intent.getStringArrayExtra("reviewAnswers") ?: return
        val score = intent.getIntExtra("score", 0)
        val total = reviewAnswers.size


        binding.tvFinalScore.text = "$score/$total"


        binding.reviewButton.setOnClickListener {


            displayResults(reviewAnswers)

            binding.reviewButton.visibility = TextView.GONE
        }


        binding.btnExit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            finish()
        }
    }

    private fun displayResults(reviewAnswers: Array<String>) {
        for ((index, answer) in reviewAnswers.withIndex()) {
            val tv = TextView(this).apply {
                textSize = 16f
                text = "${index + 1}. $answer"
            }
            binding.reviewContainer.addView(tv)
        }
    }
}
