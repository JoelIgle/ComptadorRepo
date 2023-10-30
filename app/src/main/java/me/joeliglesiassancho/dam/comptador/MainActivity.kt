package me.joeliglesiassancho.dam.comptador

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

//      Aixó és per a mostrar el comptador de clicks

        tapMeButton.setOnClickListener{
            incrementCounter()
        }
//        Aixó és per a mostrar el temps disponible
          timeTextView.text = getString(R.string.timeText, time)
    }

//    Aquesta funció fa que s'incremente el comptador a cada click
    private fun incrementCounter() {
        counter += 1
        counterTextView.text = counter.toString()
    }
}

