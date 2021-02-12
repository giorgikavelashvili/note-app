package com.example.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddNoteActivity : AppCompatActivity() {
    private lateinit var addNoteButton: Button
    private lateinit var addNoteTitle: EditText
    private lateinit var addNoteBody: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        addNoteButton = findViewById(R.id.addUserButton )
        addNoteTitle = findViewById(R.id.addNoteTitle)
        addNoteBody = findViewById(R.id.addNoteBody)

        var database: DatabaseReference = Firebase.database.getReferenceFromUrl("https://user-database-c8439-default-rtdb.firebaseio.com/")

        fun AddNewNote() {

            val newUser = Note(addNoteTitle.text.toString(),addNoteBody.text.toString())
            val child = addNoteTitle.text.toString()
            database.child("Note Database").child(child).setValue(newUser)

        }

        addNoteButton.setOnClickListener() {
            AddNewNote()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}
