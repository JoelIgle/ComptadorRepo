package me.joeliglesiassancho.dam.comptador

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.icu.text.IDNA.Info
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private val INITIAL_TIME = 5

    private val TAG = MainActivity::class.java.simpleName

    private var alertDialog: AlertDialog? = null


    internal lateinit var tapMeButton: Button
    internal lateinit var timeTextView: TextView
    internal lateinit var counterTextView: TextView
    internal var counter = 0
    internal var time = 5
    internal var appStarted = false
    internal lateinit var countdownTimer: CountDownTimer
    internal val initialCountDownTimer: Long = time.toLong() * 1000 // 60 sec
    internal val intervalCountDownTimer: Long = 1000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

        // Aquest és el botó de informació
        val infoButton = findViewById<ImageButton>(R.id.infoButton)
        infoButton.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Informació")
                .setMessage("Aquesta aplicació android ha estat feta per Joel Iglesias Sancho")
                .setPositiveButton("Tancar") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()

            dialog.show()
        }
        tapMeButton.setOnClickListener {
            if (!appStarted) {
                startGame()
            }
            incrementCounter()
            // Carga la animació d'esquerra a dreta quan toques
            val moveLeftRight = AnimationUtils.loadAnimation(this, R.anim.move_left_right)
            val moveRightLeft = AnimationUtils.loadAnimation(this, R.anim.move_right_left)

            // Aquí la aplica
            tapMeButton.startAnimation(moveLeftRight)
            tapMeButton.startAnimation(moveRightLeft)

        }

        timeTextView.text = getString(R.string.timeText, time)
        counterTextView.text = getString(R.string.puntsText, counter)

        initCountdown()
    }

    private fun startGame() {
        countdownTimer.start()
        appStarted = true
    }

    private fun initCountdown() {
        countdownTimer = object : CountDownTimer(initialCountDownTimer, intervalCountDownTimer) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeTextView.text = getString(R.string.timeText,timeLeft) //Abans simplement posaba el temps, sense text
            }

            override fun onFinish() {
                endGame()
            }
        }
    }

    private fun incrementCounter() {
        counter += 1
        counterTextView.text = getString(R.string.puntsText, counter) //Abans solament posaba el número de punts, ara amb text
    }

    private fun endGame() {
        // Mostra un AlertDialog al acabar el temps, amb la puntuació
        // Depém de la puntuació que faigue mostrarà un missatge o un altre
        if (counter < 10) {
            alertDialog = AlertDialog.Builder(this)
                .setTitle("Temps acabat,")
                .setMessage("Has fet $counter punts són molt pocs, millora")
                .setCancelable(false)
                .setPositiveButton("Tancar") { dialog, _ ->
                    dialog.dismiss()
                    resetGame()
                }
                .create()
        }else if (counter < 20) {
            alertDialog = AlertDialog.Builder(this)
                .setTitle("Temps acabat!")
                .setMessage("Has fet $counter punts, està bé però podries fer més")
                .setCancelable(false)
                .setPositiveButton("Tancar") { dialog, _ ->
                    dialog.dismiss()
                    resetGame()
                }
                .create()
        }else {
            alertDialog = AlertDialog.Builder(this)
                .setTitle("Temps acabat")
                .setMessage("Has fet $counter punts, són molts, increible")
                .setCancelable(false)
                .setPositiveButton("Tancar") { dialog, _ ->
                    dialog.dismiss()
                    resetGame()
                }
                .create()
        }
        alertDialog?.show()

//        Toast.makeText(this, getString(R.string.endGame, counter), Toast.LENGTH_LONG).show()

//        resetGame()
    }

    private fun resetGame() {
        counter = 0
        counterTextView = findViewById(R.id.counterTextView)
        time = INITIAL_TIME
        timeTextView.text = getString(R.string.timeText,time) //Abans simplement posaba el temps, sense text

        initCountdown()
        appStarted = false
    }
}


