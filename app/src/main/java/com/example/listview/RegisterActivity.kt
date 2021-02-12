package com.example.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


class RegisterActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var regButton: Button
    private lateinit var regEmail: EditText
    private lateinit var regPwd: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginButton = findViewById(R.id.toLoginButton)
        regButton = findViewById(R.id.regButton)
        regEmail = findViewById(R.id.regEmailEditText)
        regPwd = findViewById(R.id.regPwdEditText)
        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener{
            val loginIntent = Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)
        }

        regButton.setOnClickListener{
            val email: String = regEmail.text.toString()
            val password: String = regPwd.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(this,"Please fill BOTH fields.", Toast.LENGTH_LONG).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                    this,
                    OnCompleteListener{ task ->
                        if(task.isSuccessful){
                            val user = auth.currentUser
                            val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(email).build()
                            user?.updateProfile(profileUpdates)?.addOnCompleteListener(OnCompleteListener<Void?> { userUpdated ->
                                if(userUpdated.isSuccessful){
                                    Toast.makeText(this,"Registration Successful", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this,MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            })
                        }
                        else{
                            Toast.makeText(this,"Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
