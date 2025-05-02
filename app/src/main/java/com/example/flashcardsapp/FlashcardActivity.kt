package com.example.flashcardsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcardsapp.databinding.ActivityFlashcardBinding

class FlashcardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlashcardBinding

    private val questions = arrayOf(
        "The Earth revolves around the Sun.",
        "Water boils at 90°C at sea level.",
        "The human body has 206 bones.",
        "Lightning never strikes the same place twice.",
        "Sharks are mammals."
    )

    private val answers = booleanArrayOf(true, false, true, false, false)
    private var index = 0
    private var score = 0
    private var totalQuestions = questions.size
    private val reviewAnswers = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestion()

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            index++
            if (index < totalQuestions) {
                loadQuestion()
            } else {
                val intent = Intent(this, ReviewActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("reviewAnswers", reviewAnswers.toTypedArray())
                startActivity(intent)

            }
        }
    }

    private fun loadQuestion() {
        binding.questionText.text = questions[index]
        binding.feedbackText.text = ""
        binding.nextButton.isEnabled = false
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[index]
        val result = if (userAnswer == correct) {
            score++
            "Correct!"
        } else {
            "Incorrect"
        }

        reviewAnswers.add("${questions[index]} -> Correct: $correct")
        binding.feedbackText.text = result
        binding.nextButton.isEnabled = true
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false
    }
}
