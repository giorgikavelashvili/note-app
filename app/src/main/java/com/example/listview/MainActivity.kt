package com.example.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mainListView: ListView
    private lateinit var addUserButton: Button
    private lateinit var logOut: Button
    private lateinit var displayName: TextView
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //supportActionBar?.hide();

        val notes = ArrayList<Note>()
        notes.add(Note("First Note!!!!","Good job this is your first note!!!!!!!!!"))
        notes.add(Note("Note two?!??!?!?!?!","There can be more than one 0_0"))
        notes.add(Note("no more notes??","adding function doesn't work properly :(("))
        notes.add(Note("Tengiz Panchulidze","yes that is my name yes yes"))
        notes.add(Note("I tried","really did though"))

        mainListView = findViewById(R.id.mainListView)
        addUserButton = findViewById(R.id.addUserIntentButton)
        logOut = findViewById(R.id.mainLogOutButton)
        displayName = findViewById(R.id.mainDisplayName)
        auth = FirebaseAuth.getInstance()

        val addUserIntentButton = findViewById<Button>(R.id.addUserIntentButton)
        mainListView.adapter = MyAdapter(this, notes)

        mainListView.setOnItemClickListener { _, _, position, _ ->
            //Toast.makeText(this,users[position], Toast.LENGTH_SHORT).show()

            val detailIntent = Intent(this, NoteDetailActivity::class.java)
            detailIntent.putExtra(Constants.NOTE_TITLE_KEY, notes[position].Title)
            detailIntent.putExtra(Constants.NOTE_BODY_KEY, notes[position].Body)
            startActivity(detailIntent)
        }

        addUserButton.setOnClickListener{
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        logOut.setOnClickListener{
            auth.signOut()
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(auth.currentUser != null){
            displayName.text = auth.currentUser?.displayName
        }
    }
}


