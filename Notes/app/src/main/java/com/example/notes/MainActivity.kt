  package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

  class MainActivity : AppCompatActivity() {


    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNote.observe(this, Observer {List->
            List?.let {
                adapter.update(it)
            }
            
        })



    }

      fun onItemClicked(note: Note) {
          viewModel.deleteNote(note)
          Toast.makeText(this,"${note.text}Deleted" , Toast.LENGTH_LONG).show()
      }

      fun submitData(view: View) {
          val notetext = input.text.toString()
          if(notetext.isNotEmpty())
          {
              viewModel.insertNote(Note(notetext))
              Toast.makeText(this,"${notetext}Inserted" , Toast.LENGTH_LONG).show()
          }

      }
  }