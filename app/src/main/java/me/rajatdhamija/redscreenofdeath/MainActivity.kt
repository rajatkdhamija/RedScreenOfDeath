package me.rajatdhamija.redscreenofdeath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnCrash: AppCompatButton = findViewById(R.id.btnCrash)

        btnCrash.setOnClickListener {
            throw  RuntimeException("This is a crash");
        }
    }
}