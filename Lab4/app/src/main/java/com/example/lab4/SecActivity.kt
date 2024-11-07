package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).let {
                v.setPadding(it.left, it.top, it.right, it.bottom)
            }
            insets
        }

        val edDrink = findViewById<TextView>(R.id.edDrink)
        val rgSugar = findViewById<RadioGroup>(R.id.rgSugar)
        val rgIce = findViewById<RadioGroup>(R.id.rgIce)

        findViewById<Button>(R.id.btnSend).setOnClickListener {
            if (edDrink.text.isEmpty()) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle().apply {
                    putString("drink", edDrink.text.toString())
                    putString("sugar", rgSugar.findViewById<RadioButton>(
                        rgSugar.checkedRadioButtonId).text.toString())
                    putString("ice", rgIce.findViewById<RadioButton>(
                        rgIce.checkedRadioButtonId).text.toString())
                }
                setResult(RESULT_OK, Intent().putExtras(bundle))
                finish()
            }
        }
    }
}
