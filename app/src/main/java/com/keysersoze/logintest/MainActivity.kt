package com.keysersoze.logintest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    private val testUsername = "test@luxpmsoft.com"
    private val testPassword = "test1234!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLoginFinal)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email == testUsername && password == testPassword) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else if (password != testPassword) {
                showPopup("The provided password is wrong")
            }

            if (!isValidPassword(password)) {
                showValidationMessage()
            }
        }
    }

    private fun showPopup(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }

    private fun isValidPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}$")
        return regex.matches(password)
    }

    @SuppressLint("SetTextI18n")
    private fun showValidationMessage() {
        val alertText = findViewById<TextView>(R.id.textCombinationAlert)
        alertText.visibility = View.VISIBLE
        alertText.text = "Wrong combination"
    }
}
