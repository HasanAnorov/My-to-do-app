package com.example.mytodoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.RenderScript
import android.widget.Toast
import com.example.mytodoapp.R
import com.example.mytodoapp.database.DbHelper
import com.example.mytodoapp.databinding.ActivityAddingBinding
import com.example.mytodoapp.model.Note
import com.example.mytodoapp.model.Priority
import com.example.mytodoapp.room.RoomDbHelper
import com.example.mytodoapp.room.RoomUser

class AddingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddingBinding
//    private lateinit var dbHelper :DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val instance = RoomDbHelper.DatabaseBuilder.getInstance(this)

        //dbHelper = DbHelper(this)
        supportActionBar?.hide()

        binding.create.setOnClickListener {

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
//            if (title.isNotEmpty()&&deadline.isNotEmpty()&&!priority.equals(null)) shuni ishlatib kur
            if (title.isNotEmpty()&&deadline.isNotEmpty()){

                val note = Note(title,description,deadline,priority)
                val roomNote = RoomUser(0,title,description,deadline,priority)

                instance.roomDao().insert(roomNote)

                //dbHelper.insert(note)
                Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
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