package com.example.flashcardsapp
// This defines where our class lives in the project structure

// Import the Serializable interface from Java

// (Needed to pass objects between different screens in Android)

import java.io.Serializable



// Create a data class - a special class that mainly holds data

// The "Question" class will represent our flashcard questions

// ": Serializable" means this can be converted to a format Android can send between screens

data class Question(

    // The question text (can't be changed after creation - 'val' makes it read-only)

    val text: String,



    // The correct answer (true/false)

    val answer: Boolean

) : Serializable  // Implement the Serializable interface