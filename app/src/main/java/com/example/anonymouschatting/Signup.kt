package com.example.anonymouschatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Intents
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.jar.Attributes.Name

class Signup : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var mobile: EditText
    private lateinit var password: EditText
    private lateinit var signup: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth= FirebaseAuth.getInstance()
        supportActionBar?.hide()
        name=findViewById(R.id.name)
        email=findViewById(R.id.Email)
        mobile=findViewById(R.id.mobile)
        password=findViewById(R.id.Password)
        signup=findViewById(R.id.signup)

        signup.setOnClickListener{
            val name1= name.text.toString()
            val email1= email.text.toString()
            val password1=password.text.toString()
            signUp(name1,email1,password1)
        }
    }

    private fun signUp(name1: String, email1: String, password1: String){
        mAuth.createUserWithEmailAndPassword(email1, password1)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name1,email1,mAuth.currentUser?.uid!!)
                    val intent= Intent(this@Signup,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Signup,"Error 404", Toast.LENGTH_SHORT).show()
                }
            }

    }
    private fun addUserToDatabase(name1: String, email1: String, uid: String){
       mDbRef=FirebaseDatabase.getInstance().getReference()
        mDbRef.child("User").child(uid).setValue(User(name1,email1,uid))
    }
}