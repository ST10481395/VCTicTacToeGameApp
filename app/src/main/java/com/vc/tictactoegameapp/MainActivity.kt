package com.vc.tictactoegameapp

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Enum to define the two player types
    enum class Turn {
        NOUGHT, CROSS
    }

    // List to hold all 9 buttons on the game board
    private lateinit var boardList: List<Button>
    private lateinit var turnTextView: TextView

    // Track whose turn is first and who is currently playing
    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    // Score counters for both players
    private var crossesScore = 0
    private var noughtsScore = 0

    // This method runs when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the TextView that shows whose turn it is
        turnTextView = findViewById(R.id.turnTV)

        // Connect all the buttons in the grid to variables
        boardList = listOf(
            findViewById(R.id.a1),
            findViewById(R.id.a2),
            findViewById(R.id.a3),
            findViewById(R.id.b1),
            findViewById(R.id.b2),
            findViewById(R.id.b3),
            findViewById(R.id.c1),
            findViewById(R.id.c2),
            findViewById(R.id.c3),
        )

        // Show the initial turn label
        setTurnLabel()

        // Add a click listener to each button
        boardList.forEach { button ->
            button.setOnClickListener { boardTapped(it) }
        }
    }

    // This function runs when a player taps on a button
    private fun boardTapped(view: View) {
        if (view !is Button) return

        addToBoard(view)

        // Check if someone has won or the board is full (draw)
        when {
            checkForVictory(NOUGHT) -> {
                noughtsScore++
                showResult("Noughts Win!")
            }
            checkForVictory(CROSS) -> {
                crossesScore++
                showResult("Crosses Win!")
            }
            fullBoard() -> {
                showResult("Draw")
            }
        }
    }

    // Check if a player has won by matching 3 buttons
    private fun checkForVictory(symbol: String): Boolean {
        val b = boardList
        return (match(b[0], symbol) && match(b[1], symbol) && match(b[2], symbol)) || // Row A
                (match(b[3], symbol) && match(b[4], symbol) && match(b[5], symbol)) || // Row B
                (match(b[6], symbol) && match(b[7], symbol) && match(b[8], symbol)) || // Row C

                (match(b[0], symbol) && match(b[3], symbol) && match(b[6], symbol)) || // Col 1
                (match(b[1], symbol) && match(b[4], symbol) && match(b[7], symbol)) || // Col 2
                (match(b[2], symbol) && match(b[5], symbol) && match(b[8], symbol)) || // Col 3

                (match(b[0], symbol) && match(b[4], symbol) && match(b[8], symbol)) || // Diagonal \
                (match(b[2], symbol) && match(b[4], symbol) && match(b[6], symbol))    // Diagonal /
    }

    // Helper function to check if a button matches a symbol
    private fun match(button: Button, symbol: String): Boolean = button.text == symbol

    // Show a custom popup dialog with the game result
    private fun showResult(title: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_result, null)
        val titleView = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val messageView = dialogView.findViewById<TextView>(R.id.dialogMessage)
        val resetButton = dialogView.findViewById<Button>(R.id.resetButton)

        titleView.text = title
        messageView.text = "\nNoughts $noughtsScore\nCrosses $crossesScore"

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        resetButton.setOnClickListener {
            resetBoard()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

    // Clear the board for a new game
    private fun resetBoard() {
        boardList.forEach { it.text = "" }

        // Alternate who starts first
        firstTurn = if (firstTurn == Turn.NOUGHT) Turn.CROSS else Turn.NOUGHT
        currentTurn = firstTurn
        setTurnLabel()
    }

    // Check if the board is full
    private fun fullBoard(): Boolean = boardList.none { it.text.isEmpty() }

    // Add symbol (X or O) to a tapped button with animation
    private fun addToBoard(button: Button) {
        if (button.text.isNotEmpty()) return

        if (currentTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            button.setTextColor(resources.getColor(R.color.o_color, theme))
            currentTurn = Turn.CROSS
        } else {
            button.text = CROSS
            button.setTextColor(resources.getColor(R.color.x_color, theme))
            currentTurn = Turn.NOUGHT
        }

        // Animate the button (bounce effect)
        val bounceAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)
        button.startAnimation(bounceAnim)

        setTurnLabel()
    }

    // Update the label showing whose turn it is, with fade animation
    private fun setTurnLabel() {
        val turnText = if (currentTurn == Turn.CROSS) "Turn $CROSS" else "Turn $NOUGHT"
        turnTextView.apply {
            text = turnText
            animate().alpha(0f).setDuration(150).withEndAction {
                text = turnText
                animate().alpha(1f).setDuration(150)
            }
        }
    }

    // Constants for the two player symbols
    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }
}