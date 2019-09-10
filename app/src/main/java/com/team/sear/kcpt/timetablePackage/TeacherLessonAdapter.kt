package com.team.sear.kcpt.timetablePackage

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.team.sear.kcpt.R

class TeacherLessonAdapter(private val lessons: ArrayList<Lesson?>) : RecyclerView.Adapter<TeacherLessonAdapter.ViewHolder>() {

    private lateinit var itemView: View

    override fun getItemCount() = lessons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_timetable, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, p: Int) {
            holder.lessonNumTV!!.text = lessons[p]!!.lessonNum
            holder.lessonTV!!.text = lessons[p]!!.lesson
            holder.roomNumTV!!.text = lessons[p]!!.roomNum
            holder.anybodyNameTV!!.text = lessons[p]!!.groupName
            holder.lessonTimeTV!!.text = lessons[p]!!.lessonTime
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lessonNumTV: TextView? = null
        var lessonTV: TextView? = null
        var roomNumTV: TextView? = null
        var anybodyNameTV: TextView? = null
        var lessonTimeTV: TextView? = null

        init {
            lessonNumTV = itemView.findViewById(R.id.lessonNumTV)
            lessonTV = itemView.findViewById(R.id.lessonTV)
            roomNumTV = itemView.findViewById(R.id.roomNumTV)
            anybodyNameTV = itemView.findViewById(R.id.anybodyNameTV)
            lessonTimeTV = itemView.findViewById(R.id.lessonTimeTV)
        }
    }
}