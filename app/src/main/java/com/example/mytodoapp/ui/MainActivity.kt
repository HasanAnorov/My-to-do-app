package com.example.mytodoapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mytodoapp.R
import com.example.mytodoapp.adapter.OnClick
import com.example.mytodoapp.adapter.RecyclerAdapter
import com.example.mytodoapp.database.DbHelper
import com.example.mytodoapp.databinding.ActivityMainBinding
import com.example.mytodoapp.model.Note
import com.example.mytodoapp.model.Priority
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_view.view.*

class MainActivity : AppCompatActivity(),OnClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerAdapter
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dbHelper = DbHelper(this)

        titleDoes.paintFlags

        binding.addCard.setOnClickListener {
                val intent = Intent(this, AddingActivity::class.java)
                startActivity(intent)
        }

        setData(dbHelper.listOfNotes())
    }

    private fun setData(list: List<Note>){
        adapter = RecyclerAdapter(this)
        adapter.list = list
        binding.recyclerView.adapter = adapter
    }

    override fun onCheckboxClick(title: TextView,checkBox: CheckBox) {

        if (checkBox.isChecked){
            Toast.makeText(this, "${title.text} task is completed", Toast.LENGTH_SHORT).show()
            title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        }
        else{
            Toast.makeText(this, "${title.text} task is not completed", Toast.LENGTH_SHORT).show()
            title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onItemViewClick(note: Note) {

        val dialog = AlertDialog.Builder(this).create()
        val dialogView = layoutInflater.inflate(R.layout.dialog_view, null, false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogView.title.text = note.title
        dialogView.description.text = note.description
        dialogView.deadline.text=note.time
        when(note.priority){
            Priority.Low -> {
                dialogView.priority.text = "Low"
            }
            Priority.Middle -> {
                dialogView.priority.text = "Middle"
            }
            Priority.Urgent -> {
                dialogView.priority.text = "Urgent"
            }
            else -> {
                dialogView.priority.text = "Middle"
            }
        }

        dialog.setView(dialogView)

        dialogView.deleteCard.setOnClickListener {
            dbHelper.deleteRow(note)
            // dbHelper.deleteRow(note.title)
            dialog.dismiss()
            setData(dbHelper.listOfNotes())
        }

        dialogView.editCard.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("data",note.id)
            startActivity(intent)

            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.sort_by_name -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}