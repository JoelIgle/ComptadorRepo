package me.joeliglesiassancho.dam.comptador

import android.content.IntentSender.OnFinished
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.joeliglesiassancho.dam.comptador.ui.theme.ComptadorTheme

class MainActivity : ComponentActivity() {
    // Variables
    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    // Inicialitzem comptador i el temps
    internal var counter = 0
    internal var time = 60
    // Aquesta variable determina quan s'inicia, es a dir al primer click, per a començar a baixar el temps
    internal var appStarted = false
    internal lateinit var countdownTimer : CountDownTimer
    internal val initialCountDownTimer: Long = 60000 // 60 sec
    internal val intervalCountDownTimer: Long = 1000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

//      Aixó és per a mostrar el comptador de clicks

        tapMeButton.setOnClickListener{
            if (!appStarted){
                startGame()

            }
            incrementCounter()
        }
//        Aixó és per a mostrar el temps disponible
          timeTextView.text = getString(R.string.timeText, time)
    }

    private fun startGame() {
        countdownTimer.start()
        appStarted = true
    }

    private fun initCountdown() {
        countdownTimer = object : CountDownTimer(initialCountDownTimer,intervalCountDownTimer) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeTextView.text = timeLeft.toString()
            }

            override fun onFinish() {
                endGame()
            }
        }
    }

//    Aquesta funció fa que s'incremente el comptador a cada click
    private fun incrementCounter() {
        counter += 1
        counterTextView.text = counter.toString()
    }

    private fun endGame() {
        Toast.makeText(this,getString(R.string.endGame), Toast.LENGTH_LONG).show()
//        resetGame()
    }

    private fun resetGame(){

    }
}

