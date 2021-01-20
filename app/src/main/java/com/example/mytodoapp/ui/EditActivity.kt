package com.example.mytodoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mytodoapp.R
import com.example.mytodoapp.database.DbHelper
import com.example.mytodoapp.databinding.ActivityEditBinding
import com.example.mytodoapp.model.Note
import com.example.mytodoapp.model.Priority

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        dbHelper = DbHelper(this)

        val data = intent.getIntExtra("data",0)

        binding.update.setOnClickListener {

            val title = binding.etTask.text.toString()
            val description = binding.etDesc.text.toString()
            val deadline = binding.etDeadline.text.toString()
            var priority = Priority.None
            when(binding.radioGroup.checkedRadioButtonId ){
                R.id.urgent->{
                    priority = Priority.Urgent
                }
                R.id.normal->{
                    priority = Priority.Middle
                }
                R.id.low->{
                    priority = Priority.Low
                }
            }
            if (title.isNotEmpty()&&deadline.isNotEmpty()) {

                val note = Note(title, description, deadline, priority)
                dbHelper.edit(note,data)
                Toast.makeText(this, "Note edited", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, "Please fill all fields !", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancel.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}