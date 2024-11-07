package com.example.lab6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val items = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupEdgeToEdge()
        val btnToast = findViewById<Button>(R.id.btnToast)
        val btnSnackBar = findViewById<Button>(R.id.btnSnackBar)
        val btnDialog1 = findViewById<Button>(R.id.btnDialog1)
        val btnDialog2 = findViewById<Button>(R.id.btnDialog2)
        val btnDialog3 = findViewById<Button>(R.id.btnDialog3)

        btnToast.setOnClickListener { showToast("預設 Toast") }
        btnSnackBar.setOnClickListener { showSnackBar(it) }
        btnDialog1.setOnClickListener { showAlertDialog("按鈕式 AlertDialog", "AlertDialog 內容", true) }
        btnDialog2.setOnClickListener { showListDialog() }
        btnDialog3.setOnClickListener { showSingleChoiceDialog() }
    }

    private fun setupEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSnackBar(view: android.view.View) {
        Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("按鈕") { showToast("已回應") }
            .show()
    }

    private fun showAlertDialog(title: String, message: String, hasButtons: Boolean) {
        val builder = AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            if (hasButtons) {
                setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
                setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
                setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
            }
        }
        builder.show()
    }

    private fun showListDialog() {
        AlertDialog.Builder(this)
            .setTitle("列表式 AlertDialog")
            .setItems(items) { _, i -> showToast("你選的是${items[i]}") }
            .show()
    }

    private fun showSingleChoiceDialog() {
        var selectedPosition = 0
        AlertDialog.Builder(this)
            .setTitle("單選式 AlertDialog")
            .setSingleChoiceItems(items, selectedPosition) { _, i -> selectedPosition = i }
            .setPositiveButton("確定") { _, _ -> showToast("你選的是${items[selectedPosition]}") }
            .show()
    }
}
