package com.example.anonymouschatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.FirebaseAuth
import java.net.PasswordAuthentication

class Login : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var signup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.Email)
        password = findViewById(R.id.Password)
        login = findViewById(R.id.login)
        signup = findViewById(R.id.signup)

        signup.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            val email1 = email.text.toString()
            val password1 = password.text.toString()
            logIn(email1, password1)
        }
    }

    private fun logIn(email1: String, password1: String) {

        mAuth.signInWithEmailAndPassword(email1, password1)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent= Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login,"This user does not exist", Toast.LENGTH_SHORT).show()
                }
            }


    }
}

