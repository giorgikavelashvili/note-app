package com.example.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var regButton: Button
    private lateinit var loginButton: Button
    private lateinit var loginEmail: EditText
    private lateinit var loginPwd: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.LoginButton)
        regButton = findViewById(R.id.toRegButton)
        loginEmail = findViewById(R.id.loginEmailEditText)
        loginPwd = findViewById(R.id.loginPwdEditText)
        auth = FirebaseAuth.getInstance()

        regButton = findViewById(R.id.toRegButton)
        regButton.setOnClickListener{
            finish()
        }  //Back to register

        loginButton.setOnClickListener{
            val email: String = loginEmail.text.toString()
            val password: String = loginPwd.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(this,"Please fill BOTH fields.", Toast.LENGTH_LONG).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }    //Login code
    }
}