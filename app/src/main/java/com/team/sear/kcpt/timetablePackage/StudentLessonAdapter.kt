package com.team.sear.kcpt.timetablePackage

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.team.sear.kcpt.R
import com.team.sear.kcpt.R.color.*

class StudentLessonAdapter(private val lessons: ArrayList<Lesson?>) : RecyclerView.Adapter<StudentLessonAdapter.ViewHolder>() {

    private lateinit var itemView: View

    override fun getItemCount() = lessons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_timetable, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, p: Int) {

        holder.roomNum!!.text = lessons[p]!!.roomNum
        holder.lessonNum!!.text = lessons[p]!!.lessonNum
        holder.teacherName!!.text = lessons[p]!!.teacherName
        holder.lessonTime!!.text = lessons[p]!!.lessonTime
        when {
            lessons[p]!!.groupOrSubGroup == "subGroup1" -> {
                holder.lesson!!.text = "1 Подгруппа\n\n" + lessons[p]!!.lesson
            }
            lessons[p]!!.groupOrSubGroup == "subGroup2" -> {
                holder.lesson!!.text = "2 Подгруппа\n\n" + lessons[p]!!.lesson
            }
            lessons[p]!!.groupOrSubGroup == "allGroup" -> holder.lesson!!.text = lessons[p]!!.lesson

        }
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