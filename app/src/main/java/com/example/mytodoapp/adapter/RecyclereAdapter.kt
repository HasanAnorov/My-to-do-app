package com.example.mytodoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodoapp.R
import com.example.mytodoapp.model.Note
import com.example.mytodoapp.model.Priority
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerAdapter(val listener:OnClick): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var list :List<Note> = listOf()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(note:Note){
            itemView.title.text = note.title
            itemView.deadline_tv.text = note.time
            when (note.priority){

                Priority.Low ->{
                    itemView.priority_tv.text = "Low"
                }
                Priority.Middle ->{
                    itemView.priority_tv.text = "Middle"
                }
                Priority.Urgent ->{
                    itemView.priority_tv.text = "Urgent"
                }
            }

            itemView.setOnClickListener {
                listener.onItemViewClick(note)
            }

            itemView.checkbox.setOnClickListener {
                listener.onCheckboxClick(itemView.title,itemView.checkbox)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size



}

interface OnClick{
    fun  onCheckboxClick(title:TextView,checkBox: CheckBox)
    fun  onItemViewClick(note:Note)
}