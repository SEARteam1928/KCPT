package com.team.sear.kcpt.timetablePackage

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.team.sear.kcpt.R

class StudentLessonAdapter(private val lessons: ArrayList<Lesson?>) : RecyclerView.Adapter<StudentLessonAdapter.ViewHolder>() {

    private lateinit var itemView: View

    override fun getItemCount() = lessons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_timetable, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, p: Int) {
            holder.lessonNum!!.text = lessons[p]!!.lessonNum
            holder.lesson!!.text = lessons[p]!!.lesson
            holder.roomNum!!.text = lessons[p]!!.roomNum
            holder.teacherName!!.text = lessons[p]!!.teacherName
            holder.lessonTime!!.text = lessons[p]!!.lessonTime
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lessonNum: TextView? = null
        var lesson: TextView? = null
        var roomNum: TextView? = null
        var teacherName: TextView? = null
        var lessonTime: TextView? = null
        init {
            lessonNum = itemView.findViewById(R.id.lessonNumTV)
            lesson = itemView.findViewById(R.id.lessonTV)
            roomNum = itemView.findViewById(R.id.roomNumTV)
            teacherName = itemView.findViewById(R.id.anybodyNameTV)
            lessonTime = itemView.findViewById(R.id.lessonTimeTV)
        }
    }
}