package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NoteDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val noteTitle = intent.getStringExtra(Constants.NOTE_TITLE_KEY);
        val noteBody = intent.getStringExtra(Constants.NOTE_BODY_KEY)

        val noteTitleTextView = findViewById<TextView>(R.id.noteTitleTextView);
        val noteBodyTextView = findViewById<TextView>(R.id.noteBodyTextView);

        noteTitleTextView.text = noteTitle
        noteBodyTextView.text = noteBody
    }
}